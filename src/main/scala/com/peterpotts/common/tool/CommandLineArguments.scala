package com.peterpotts.common.tool

/**
 * A simple command line arguments parser.
 */
object CommandLineArguments {
  def apply(args: Array[String]): (Map[String, String], List[String]) = {
    val (options, arguments) = args.partition(_ startsWith "--")

    def split(option: String): (String, String) =
      option.stripPrefix("--").split("=") match {
        case Array(key, value) => key -> value
        case Array(key) => key -> "true"
        case unmatched => throw new MatchError(unmatched)
      }

    (options.map(split).toMap, arguments.toList)
  }
}
