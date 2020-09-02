package CensusAnalyzerProject
import com.google.gson.Gson

class CensusAnalyzer {
  var censusMap:Map[String,IndiaStateCensusDAO] = Map()
  var censusStateMap:Map[String,IndiaStateCensusDAO] = Map()

  def loadCensusData(filepath:String):Int = {
    censusMap = new CensusLoader().loadData(classOf[IndiaStateCensus],filepath)
    censusMap.size
  }
  def loadCensusStateData(filepath:String):Int = {
    censusStateMap =  new CensusLoader().loadData(classOf[IndianStateCode],filepath)
    censusStateMap.size
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
      case 4 => censusCSVList.sortBy(_.densityPerSqKm).reverse
      case 5 =>censusCSVList.sortBy(_.areaInSqKm).reverse
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def getStateCodeWiseSortedCensusData():String = {
    for(statenameCensus <- censusMap.keys;statename <- censusStateMap.keys;if(statename.equals(statenameCensus)) == true){
      var censusData = censusMap(statenameCensus)
      censusData.stateCode = censusStateMap(statename).stateCode
    }
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
