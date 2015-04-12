package com.peterpotts.common.util

case class Template(template: String) {
  def parse(parameters: Map[String, String]): String =
    parameters.foldLeft(template) {
      case (intermediate, (name, value)) => intermediate.replace(s"<$name>", value)
    }
}
