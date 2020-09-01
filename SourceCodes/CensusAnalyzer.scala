package CensusAnalyzerProject
import java.util
import java.util.Comparator
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

  def sort(censusComparator: Comparator[IndiaStateCensusDAO]):String = {
    if (censusMap == null || censusMap.size == 0) {
      throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.noCensusData)
    }
    val n = censusMap.size
    val censusCSVList = censusMap.values.toArray
    for (i <- 0 until n - 1) {
      for (j <- 0 until n - i - 1) {
        val census1 = censusCSVList(j)
        val census2 = censusCSVList(j + 1)
        if (censusComparator.compare(census1, census2) > 0) {
          censusCSVList(j) = census2
          censusCSVList(j+1) = census1
        }
      }
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def getStateCodeWiseSortedCensusData():String = {
    for(statenameCensus <- censusMap.keys;statename <- censusStateMap.keys;if(statename.equals(statenameCensus)) == true){
      var censusData = censusMap(statenameCensus)
      censusData.stateCode = censusStateMap(statename).stateCode
    }
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(o1: IndiaStateCensusDAO, o2: IndiaStateCensusDAO): Int = {
        o1.stateCode.compareTo(o2.stateCode)
      }
    }
    sort(censusComparator)
  }

  def getStateWiseSortedCensusData():String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(o1: IndiaStateCensusDAO, o2: IndiaStateCensusDAO): Int = {
        o1.state.compareTo(o2.state)
      }
    }
    sort(censusComparator)
  }

  def getPopulationDensityWiseSortedCensusData():String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(o1: IndiaStateCensusDAO, o2: IndiaStateCensusDAO): Int = {
        o1.densityPerSqKm.compareTo(o2.densityPerSqKm)
      }
    }
    sort(censusComparator.reversed())
  }

  def getPopulationWiseSortedCensusData():String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(o1: IndiaStateCensusDAO, o2: IndiaStateCensusDAO): Int = {
        o1.population.compareTo(o2.population)
      }
    }
    sort(censusComparator.reversed())
  }
  def getAreaWiseSortedCensusData():String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(o1: IndiaStateCensusDAO, o2: IndiaStateCensusDAO): Int = {
        o1.areaInSqKm.compareTo(o2.areaInSqKm)
      }
    }
    sort(censusComparator.reversed())
  }

  def getCountRows[T](fileiterator: util.Iterator[T]):Int = {
    var countRows = 0
    while(fileiterator.hasNext()) {
      countRows += 1
      fileiterator.next()
    }
    countRows
  }
}
