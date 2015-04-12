package com.peterpotts.common.sample

object SampleMoney extends MapSample[Int, BigDecimal](SampleInt(10000), cents => BigDecimal(cents) / 100)
