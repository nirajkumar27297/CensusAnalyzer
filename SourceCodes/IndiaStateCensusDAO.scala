package CensusAnalyzerProject

class IndiaStateCensusDAO {

  var state:String = null
  var areaInSqKm = 0
  var densityPerSqKm = 0
  var population = 0
  var stateCode:String = null

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
}
