package com.motiondesign.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motiondesign.exception.InvalidPlanException;
import com.motiondesign.formatter.Formattable;
import com.motiondesign.formatter.PackFormatter;
import com.motiondesign.model.InputData;
import com.motiondesign.model.Item;
import com.motiondesign.model.Constraint;
import com.motiondesign.model.Pack;
import com.motiondesign.model.PackStack;
import com.motiondesign.util.Constants;

/**
 * @author Rasanka Jayawardana
 * Pack planner implementation class 
 * 
 */
public class PackPlannerImpl implements IPackPlanner {

	private static final Logger logger = LoggerFactory.getLogger(PackPlannerImpl.class);
	private static final PackPlannerImpl SERVICE = new PackPlannerImpl();
	private Formattable<Pack> packFormatter;

	public static PackPlannerImpl getInstancec() {
		return SERVICE;
	}

	private PackPlannerImpl() {
		this.packFormatter = PackFormatter.getInstance();
	}

	@Override
	public PackStack plan(InputData data) throws InvalidPlanException {
		if (data == null || data.getItemList().isEmpty()) {
			throw new InvalidPlanException("Invalid Input Data!");
		}
		sortItems(data);
		return generatePacks(data);
	}

	/**
	 * Sorting the item list by item length based on the user input
	 * @param inputData
	 * @throws InvalidPlanException
	 */
	private void sortItems(InputData inputData) throws InvalidPlanException {
		if (inputData.getConstraint() == null) {
			throw new InvalidPlanException("Invalid user data, please provide valid data!");
		}
		logger.debug("Sorting Item List");
		switch (inputData.getConstraint().getSortOrder()) {
		case SHORT_TO_LONG:
			Collections.sort(inputData.getItemList());
			break;
		case LONG_TO_SHORT:
			Collections.sort(inputData.getItemList());
			Collections.reverse(inputData.getItemList());
			break;
		case NATURAL:
			break;
		default:
			break;
		}
	}

	/**
	 * Generating item packs and storing the packs in a Stack
	 * @param inputData
	 * @return
	 * @throws InvalidPlanException
	 */
	private PackStack generatePacks(InputData inputData) throws InvalidPlanException {
		logger.info("Generating packs!");
		PackStack packStack = new PackStack();
		Pack pack;
		int maxItemsPerPack = inputData.getConstraint().getMaxItems();
		float maxWeightPerPack = inputData.getConstraint().getMaxWeight();

		logger.debug("Constraints, MAX Items per pack {}, MAX Weight per pack {} ", maxItemsPerPack, maxWeightPerPack);

		for (Item item : inputData.getItemList()) {

			pack = getTopPack(packStack, inputData.getConstraint(), false);
			float nextItemWeight = item.getTotalWeight();
			boolean addToTopPack = (maxItemsPerPack > item.getQuantity() && nextItemWeight <= maxWeightPerPack);

			int packBalanceQty = maxItemsPerPack - pack.getTotalPackQty();
			float balaceWeightInPack = maxWeightPerPack - pack.getTotalPackWeight();
			int tempItemCount = 0;
			if (addToTopPack == false && packBalanceQty > 0 && balaceWeightInPack > 0) {
				float tempItemsTotalWeight = 0.0f;
				for (int i = 1; i <= item.getQuantity(); i++) {
					tempItemsTotalWeight += item.getWeight();
					if (i <= packBalanceQty && tempItemsTotalWeight <= balaceWeightInPack) {
						tempItemCount += 1;
					} else {
						break;
					}
				}
				Item tempItem = new Item();
				tempItem.setId(item.getId());
				tempItem.setLength(item.getLength());
				tempItem.setQuantity(tempItemCount);
				tempItem.setWeight(item.getWeight());

				pack.getItemStack().push(tempItem);
				item.setQuantity(item.getQuantity() - tempItemCount);
			}

			pack = getTopPack(packStack, inputData.getConstraint(), !addToTopPack);

			if (item.getQuantity() <= maxItemsPerPack && item.getTotalWeight() <= maxWeightPerPack
					&& pack.getTotalPackWeight() <= maxWeightPerPack && pack.getTotalPackQty() <= maxItemsPerPack
					&& (pack.getTotalPackQty() + item.getQuantity()) <= maxItemsPerPack) {

				pack.addItem(item);
			} else {

				Item tempItem = new Item();
				tempItem.setId(item.getId());
				tempItem.setLength(item.getLength());
				tempItem.setWeight(item.getWeight());

				if (item.getQuantity() > maxItemsPerPack && item.getTotalWeight() <= maxWeightPerPack) {
					tempItem.setQuantity(maxItemsPerPack);
					pack.addItem(tempItem);

					item.setQuantity(item.getQuantity() - maxItemsPerPack);
					pack = getTopPack(packStack, inputData.getConstraint(), true);
					pack.addItem(item);

				} else if (item.getTotalWeight() > maxWeightPerPack && item.getQuantity() <= maxItemsPerPack) {

					float tempTotalWeight = 0.0f;
					tempItemCount = 0;
					for (int i = 1; i <= item.getQuantity(); i++) {
						tempTotalWeight += item.getWeight();
						if (tempTotalWeight > maxWeightPerPack) {
							tempItemCount = i - 1;
							break;
						}
					}
					tempItem.setQuantity(tempItemCount);
					pack.addItem(tempItem);

					item.setQuantity(item.getQuantity() - tempItemCount);
					pack = getTopPack(packStack, inputData.getConstraint(), true);
					pack.addItem(item);

				} else {
					packBalanceQty = maxItemsPerPack - pack.getTotalPackQty();
					balaceWeightInPack = maxWeightPerPack - pack.getTotalPackWeight();
					tempItemCount = 1;
					float tempTotalWeight = 0.0f;
					for (int i = 1; i <= item.getQuantity(); i++) {

						tempTotalWeight += item.getWeight();
						if (i > packBalanceQty || tempTotalWeight > balaceWeightInPack) {
							tempItemCount = i - 1;
							break;
						}
					}

					tempItem.setQuantity(tempItemCount);
					pack.addItem(tempItem);

					item.setQuantity(item.getQuantity() - tempItemCount);
					pack = getTopPack(packStack, inputData.getConstraint(), true);

					pack.addItem(item);
				}

			}

			logger.info("Item {} added to the pack {}!", item.getId(), pack.getPackId());
		}
		return packStack;
	}

	/**
	 * Retrieving the top pack from the stack
	 * @param packStack
	 * @param constraint
	 * @param isNewPack
	 * @return
	 */
	private Pack getTopPack(PackStack packStack, Constraint constraint, boolean isNewPack) {
		if (packStack.isEmpty() || isNewPack) {
			Pack pack = createPack(packStack.getTopPackId(), constraint);
			addToPackStack(packStack, pack);
		}
		return packStack.getTopPack();
	}

	/**
	 * Adding the pack to the stack
	 * @param packStack
	 * @param pack
	 */
	private void addToPackStack(PackStack packStack, Pack pack) {
		packStack.addPack(pack);
		logger.info("New Pack {} added to stack. Now contains {} packs ", pack.getPackId(),
				packStack.getPackStack().size());
	}

	/**
	 * Creating the basic pack
	 * @param packId
	 * @param constraint
	 * @return
	 */
	private Pack createPack(int packId, Constraint constraint) {
		Pack pack = new Pack();
		pack.setPackId(packId + Constants.ID_INCREMENTOR);

		logger.debug("PACK ID {}", pack.getPackId());
		logger.debug("ITEMS IN PACK {} ", pack.getItemStack().size());

		logger.info("Pack {} created!", pack.getPackId());
		return pack;
	}

	/**
	 * Printing the pack plan
	 */
	@Override
	public void printPlan(PackStack packStack) throws InvalidPlanException {
		if (packStack != null) {
			logger.info("Rendering pack information!");
			StringBuilder message = new StringBuilder();
			message.append(Constants.OUTPUT_MESSAGE);
			for (Pack pack : packStack.getPackStack()) {
				if (pack != null) {
					message.append(packFormatter.formatData(pack));
				}
			}
			System.out.println(message.toString());
		}
	}
}
