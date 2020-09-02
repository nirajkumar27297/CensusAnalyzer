package CensusAnalyzerProject

class IndiaStateCensusDAO {

  var state:String = null
  var areaInSqKm:Double = 0
  var densityPerSqKm:Double = 0
  var population:Double = 0
  var stateCode:String = null
  var housingUnits:Double = 0
  var waterArea:Double = 0
  var landArea:Double = 0
  var housingDensity:Double = 0

  def this(indiaCensusCSV:IndiaStateCensus) {
    this()
    state = indiaCensusCSV.state
    areaInSqKm = indiaCensusCSV.areaInSqKm
    densityPerSqKm = indiaCensusCSV.densityPerSqKm
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
    areaInSqKm = usCensusCSV.totalArea
    densityPerSqKm = usCensusCSV.populationDensity
    population = usCensusCSV.population
    housingUnits = usCensusCSV.housingUnits
    waterArea = usCensusCSV.waterArea
    landArea = usCensusCSV.landArea
    housingDensity = usCensusCSV.housingDensity
  }
}
