package com.peterpotts.common.sample

object SampleAlphanumeric extends MapSample[Int, Char](SampleInt(62), { index =>
  if (index < 26)
    ('A' + index).toChar
  else if (index < 52)
    ('a' + index - 26).toChar
  else
    ('0' + index - 52).toChar
})
