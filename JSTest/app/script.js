function evaluateDivisors(a, b, k) {
	numDivisorsEqualToK = 0;
	
	var first = Math.sqrt(a);
	var firstSquare = Math.pow(Math.ceil(first), 2);
	var last = Math.sqrt(b);
	var lastSquare = Math.pow(Math.floor(last), 2);

	for (var i = firstSquare; i <= lastSquare; i = iterateToNextSquare(i)) {
		primes = findPrimeFactors(i);
		if (isNumDivisorsEqualToK(primes, k)) {
			numDivisorsEqualToK++;
		}
	}

	return numDivisorsEqualToK;
}

function isNumDivisorsEqualToK(primes, k) {

	numDivisors = 1;

	for (var key of primes.keys()) {
		numDivisors *= primes.get(key) + 1;
	}

	return numDivisors == k;
}

function iterateToNextSquare(i) {
	var currentSquare = Math.sqrt(i);
	var nextSquare = Math.pow(currentSquare + 1, 2);

	return nextSquare;
}

function findPrimeFactors(n) {
	var primes = new Map();

	while (n % 2 == 0) {
		addToPrimeFactors(primes, 2);

		n /= 2;
	}

	if (n != 1) {
		formatNumber(primes, n);
	}

	return primes;
}

function formatNumber(primes, n) {
	var start = Math.ceil(Math.sqrt(n));

	for (var i = start; i <= n; i++) {
		var b = Math.pow(i, 2) - n;

		if (isPerfectSquare(b) || b == 0) {

			var factor1 = i - Math.sqrt(b);
			var factor2 = i + Math.sqrt(b);

			if (factor1 != 1 && factor2 != n) {
				if (factor1 != 1) {
					formatNumber(primes, factor1);
				}
				formatNumber(primes, factor2);
			} else {
				if (factor1 != 1) {
					addToPrimeFactors(primes, factor1);
				}
				addToPrimeFactors(primes, factor2);
			}
			break;
		}
	}

}

function addToPrimeFactors(primes, factor) {
	if (primes.has(factor)) {
		var newValue = primes.get(factor) + 1;
		primes.set(factor, newValue);
	} else {
		primes.set(factor, 1);
	}
}

function isPerfectSquare(n) {
	return (Math.sqrt(n) % 1) === 0;
}