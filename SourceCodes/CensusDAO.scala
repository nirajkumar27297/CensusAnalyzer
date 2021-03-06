package CensusAnalyzerProject

class CensusDAO {

  var state:String = null
  var totalArea:Double = 0
  var populationDensity:Double = 0
  var population:Double = 0
  var stateCode:String = null
  def this(indiaCensusCSV:IndiaStateCensusDTO) {
    this()
    state = indiaCensusCSV.state
    totalArea = indiaCensusCSV.areaInSqKm
    populationDensity = indiaCensusCSV.densityPerSqKm
    population = indiaCensusCSV.population
  }

  def this(indiaStateCodeCSV:IndianStateCodeDTO) {
    this()
    state = indiaStateCodeCSV.stateName
    stateCode = indiaStateCodeCSV.stateCode
  }

  def this(usCensusCSV:USCensusDTO) {
    this()
    stateCode = usCensusCSV.stateId
    state = usCensusCSV.state
    totalArea = usCensusCSV.totalArea
    populationDensity = usCensusCSV.populationDensity
    population = usCensusCSV.population
  }
}
