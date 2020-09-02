package CensusAnalyzerProject

class CensusDAO {

  var state:String = null
  var totalArea:Double = 0
  var populationDensity:Double = 0
  var population:Double = 0
  var stateCode:String = null
  var housingUnits:Double = 0
  var waterArea:Double = 0
  var landArea:Double = 0
  var housingDensity:Double = 0

  def this(indiaCensusCSV:IndiaStateCensus) {
    this()
    state = indiaCensusCSV.state
    totalArea = indiaCensusCSV.areaInSqKm
    populationDensity = indiaCensusCSV.densityPerSqKm
    population = indiaCensusCSV.population
  }

  def this(indiaStateCodeCSV:IndianStateCode) {
    this()
    state = indiaStateCodeCSV.stateName
    stateCode = indiaStateCodeCSV.stateCode
  }

  def this(usCensusCSV:USCensus) {
    this()
    stateCode = usCensusCSV.stateId
    state = usCensusCSV.state
    totalArea = usCensusCSV.totalArea
    populationDensity = usCensusCSV.populationDensity
    population = usCensusCSV.population
    housingUnits = usCensusCSV.housingUnits
    waterArea = usCensusCSV.waterArea
    landArea = usCensusCSV.landArea
    housingDensity = usCensusCSV.housingDensity
  }
}
