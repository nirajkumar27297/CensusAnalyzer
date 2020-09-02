package CensusAnalyzerProject
import CensusAnalyzerProject.Country.Country
import com.google.gson.Gson

class CensusAnalyzer {
  var censusMap:Map[String,CensusDAO] = Map()


  def loadCensusData(country: Country,filepath:String*):Int = {
    censusMap = new CensusAdapter().loadData(country,filepath)
    censusMap.size
  }

  def sort(choice:Int):String = {
    if (censusMap == null || censusMap.size == 0) {
      throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.noCensusData)
    }
    var censusCSVList = censusMap.values.toArray
    censusCSVList = choice match {
      case 1 => censusCSVList.sortBy(_.state)
      case 2 => censusCSVList.sortBy(_.stateCode)
      case 3 => censusCSVList.sortBy(_.population).reverse
      case 4 => censusCSVList.sortBy(_.populationDensity).reverse
      case 5 => censusCSVList.sortBy(_.totalArea).reverse
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def getStateCodeWiseSortedCensusData():String = {
    sort(2)
    }

  def getStateWiseSortedCensusData():String = {
    sort(1)
  }

  def getPopulationDensityWiseSortedCensusData():String = {
    sort(4)
  }

  def getPopulationWiseSortedCensusData():String = {
    sort(3)
  }
  def getAreaWiseSortedCensusData():String = {
    sort(5)
  }
}
