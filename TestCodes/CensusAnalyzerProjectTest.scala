import org.scalatest.FunSuite
import CensusAnalyzerProject.{CensusAnalyzer, CensusAnalyzerExceptionEnum, IndiaStateCensus}
import com.google.gson.Gson

class CensusAnalyzerProjectTest extends FunSuite{

  test("test_GivenInput_IndiaStateCensusFileWithRightPath_MatchingNumberOfRows_ReturnNumberOfRows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv") == 29)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputPathIsWrong_RaiseIncorrectFilePathException") {
    val thrown = intercept[Exception] {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputFileTypeIsWrong_RaiseIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.txt")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputFileDelimiterIsWrong_RaiseUnableToParseException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongDelimiter.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputFileFieldsIsWrong_RaiseUnableToParseException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongFields.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
    }

  test("test_GivenInput_IndiaStateCodeFileWithRightPath_MatchingNumberOfRows_ReturnNumberOfRows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCensusStateData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv") == 37)
  }

  test("test_GivenInput_IndiaStateCode_WithInputPathIsWrong_RaiseIncorrectFilePathException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusStateData("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_GivenInput_IndiaStateCode_WithInputFileTypeIsWrong_RaiseIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusStateData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.txt")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_GivenInput_IndiaStateCode_WithInputFileDelimiterIsWrong_RaiseUnableToParseException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusStateData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCodeWrongDelimiter.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_GivenInput_IndiaStateCode_WithInputFileFieldsIsWrong_RaiseUnableToParseException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCensusStateData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCodeWrongFields.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_GivenInput_IndianCensusData_SortedOnStates_ReturnSortedResult") {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    val sortedCensusData = objCensus.getStateWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }

  test("test_InputEmptyData_SortedOnStates_RaisenoCensusDataException") {
    val objCensus = new CensusAnalyzer()
    val thrown = intercept[Exception] {
      val sortedCensusData = objCensus.getStateWiseSortedCensusData()
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.noCensusData.toString)
  }

  test("test_GivenInput_IndianCensusDataAndIndianStateCodes_SortedOnStatesCodes_ReturnSortedResult") {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    objCensus.loadCensusStateData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")

    val sortedCensusData = objCensus.getStateCodeWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }

  test("test_InputEmptyData_SortedOnStatesCodes_RaisenoCensusDataException") {
    val objCensus = new CensusAnalyzer()
    val thrown = intercept[Exception] {
      val sortedCensusData = objCensus.getStateCodeWiseSortedCensusData()
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.noCensusData.toString)
  }

  test("test_GivenInput_IndianCensusData_SortedOnPopulationDensityDecreasingOrder_ReturnSortedResult") {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    val sortedCensusData = objCensus.getPopulationDensityWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state == "Bihar")
    assert(censusCSV.last.state == "Arunachal Pradesh")
  }

  test("test_InputEmptyData_SortedOnPopulationDensityDecreasingOrder_RaisenoCensusDataException") {
    val objCensus = new CensusAnalyzer()
    val thrown = intercept[Exception] {
      val sortedCensusData = objCensus.getStateCodeWiseSortedCensusData()
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.noCensusData.toString)
  }
}
