package Week3.AssignmentTaxiPark

/**
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    allDrivers.filterNot { eachDriver -> trips.any { trip -> trip.driver == eachDriver } }.toSet()

// allDrivers - trips.map { it.driver }

/**
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    allPassengers.filter { eachPassenger -> trips.count { trip -> eachPassenger in trip.passengers } >= minTrips }
        .toSet()

// trips.flatMap(Trip::passengers).groupBy { passenger -> passenger }.filterValues{ group -> group.size >= minTrips }.keys


/**
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    allPassengers.filter { eachPassenger -> trips.count { trip -> eachPassenger in trip.passengers && trip.driver == driver } > 1 }
        .toSet()

// trips.filter{trip -> trip.driver == driver }.flatMap(Trip::passengers).groupBy{ passenger -> passenger }.filterValues{group -> group.size > 1}.keys


/**
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    allPassengers.filter { eachPassenger ->
        val passengerTrips = trips.filter { trips -> eachPassenger in trips.passengers }

        passengerTrips.count { it.discount != null } > passengerTrips.size / 2
    }.toSet()

// val(tripsWithDiscount,tripsWithoutDiscount) = trips.partition { it.discount !=  null }
// return allPassengers.filter { eachPassenger ->
//        tripsWithDiscount.count{ eachPassenger in it.passengers} >  tripsWithoutDiscount.count{ eachPassenger in it.passengers}
//   }.toSet()


/**
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there are no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {

//    return val value = trips.groupBy {
//        val start = it.duration / 10 * 10
//        val end = start + 9
//        start..end
//    }.maxBy { (_, group) -> group.size }?.key

    return if (trips.isNotEmpty()) {
        val durations = trips.map { it.duration - it.duration % 10 }

        val value = durations.maxByOrNull { eachDuration -> durations.count { eachDuration == it } } as Int
        value..value + 9
    } else {
        null
    }
}

/**
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {

//    if (trips.isEmpty()) return false
//
//    val totalIncome = trips.sumByDouble({ it.cost }) //[1]
//    val sortedDriversIncome: List<Double> = trips
//        .groupBy({ trip -> trip.driver }) // [2]
//        .map { (_, tripsByDriver) -> tripsByDriver.sumByDouble({it.cost}) }  // [3]
//        .sortedDescending()
//
//    val numberOfTopDrivers = (0.2 * allDrivers.size).toInt()
//    val incomeByTopDrivers = sortedDriversIncome
//        .take(numberOfTopDrivers)  // [4]
//        .sum()
//
//    return incomeByTopDrivers >= 0.8 * totalIncome

    return if (trips.isNotEmpty()) {
        val twentyPercentOfDrivers = (allDrivers.size * 0.2).toInt()

        val eightyPercentOfIncome = trips.sumOf { it.cost } * 0.8

        val driverIncomePair =
            allDrivers.map { eachDriver -> eachDriver to trips.sumOf { if (it.driver == eachDriver) it.cost else 0.0 } }

        val sortedPairs = driverIncomePair.sortedByDescending { it.second }.subList(0, twentyPercentOfDrivers)
        sortedPairs.sumOf { it.second } >= eightyPercentOfIncome
    } else false

}