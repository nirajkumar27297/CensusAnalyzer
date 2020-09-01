import org.scalatest.FunSuite
import CensusAnalyzerProject.{CensusAnalyzer, CensusAnalyzerExceptionEnum, IndiaStateCensus}
import com.google.gson.Gson

class CensusAnalyzerProjectTest extends FunSuite{

  test("test_IndiaStateCensus_MatchingNumberOfRows_Input_CSVFileWithRightPath_ReturnNumberOfRows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv") == 29)
  }

  test("test_IndiaStateCensus_InputFilePathIsWrong_ReturnIncorrectFilePathException") {
    val thrown = intercept[Exception] {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCSVDataIndiaStateCensus("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_IndiaStateCensus_InputFileTypeIsWrong_ReturnIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.txt")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_IndiaStateCensus_InputFileDelimiterWrong_ReturnIncorrectDelimiterException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongDelimiter.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_IndiaStateCensus_InputFileFieldsWrong_ReturnIncorrectFieldsException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongFields.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
    }

  test("test_IndiaStateCode_MatchingNumberOfRows_Input_CSVFileWithRightPath_ReturnNumberOfrows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCSVDataIndiaStateCode("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv") == 37)
  }

  test("test_InputFilePathIsWrong_ReturnIncorrectFilePathException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCode("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_InputFileTypeIsWrong_ReturnIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCode("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.txt")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_InputFileDelimiterWrong_ReturnIncorrectDelimiterException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCode("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCodeWrongDelimiter.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_InputFileFieldsWrong_ReturnIncorrectFieldsException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCodeWrongFields.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_InputIndianCensusData_SortedOnStates_ReturnSortedResult") {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    val sortedCensusData = objCensus.getStateWiseSortedStatesData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }

  test("test_InputEmptyData_SortedOnStates_ReturnException") {
    val objCensus = new CensusAnalyzer()
    val thrown = intercept[Exception] {
      val sortedCensusData = objCensus.getStateWiseSortedStatesData()
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.noCensusData.toString)
  }
}
