package me.sivieri.aoc2023.common

import org.hamcrest.Matchers.*

internal fun between(a: Double, b: Double) = allOf(greaterThanOrEqualTo(a), lessThanOrEqualTo(b))
