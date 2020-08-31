package CensusAnalyzerProject

object CSVBuilderFactory {
  def createCSVBuilder():TraitCSVBuilder = {
    new OpenCSVBuilder()
  }
}
