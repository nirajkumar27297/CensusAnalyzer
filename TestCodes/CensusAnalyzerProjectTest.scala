import org.scalatest.FunSuite
import CensusAnalyzerProject.{CensusAnalyzer, CensusAnalyzerExceptionEnum, IndiaStateCensus}
import com.google.gson.Gson

class CensusAnalyzerProjectTest extends FunSuite{

  test("test_GivenInput_IndiaStateCensusFileWithRightPath_MatchingNumberOfRows_ReturnNumberOfRows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv"
    ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv") == 29)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputPathIsWrong_RaiseIncorrectFilePathException") {
    val thrown = intercept[Exception] {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv",
      "./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputFileTypeIsWrong_RaiseIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.txt"
        ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputFileDelimiterIsWrong_RaiseUnableToParseException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongDelimiter.csv"
        ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_GivenInput_IndiaStateCensus_WithInputFileFieldsIsWrong_RaiseUnableToParseException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongFields.csv"
        ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
    }


  test("test_GivenInput_IndianCensusData_SortedOnStates_ReturnSortedResult") {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv"
      ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
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
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv"
      ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")

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
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv"
      ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    val sortedCensusData = objCensus.getPopulationDensityWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state == "Bihar")
    assert(censusCSV.last.state == "Arunachal Pradesh")
  }

  test("test_InputEmptyData_SortedOnPopulationDensityDecreasingOrder_RaisenoCensusDataException") {
    val objCensus = new CensusAnalyzer()
    val thrown = intercept[Exception] {
      val sortedCensusData = objCensus.getPopulationDensityWiseSortedCensusData()
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.noCensusData.toString)
  }

  test("test_GivenInput_IndianCensusData_SortedOnPopulationDecreasingOrder_ReturnSortedResult") {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv"
      ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    val sortedCensusData = objCensus.getPopulationWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state == "Uttar Pradesh")
    assert(censusCSV.last.state == "Sikkim")
  }

  test("test_InputEmptyData_SortedOnPopulationDecreasingOrder_RaisenoCensusDataException") {
    val objCensus = new CensusAnalyzer()
    val thrown = intercept[Exception] {
      val sortedCensusData = objCensus.getPopulationWiseSortedCensusData()
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.noCensusData.toString)
  }

  test("test_GivenInput_IndianCensusData_SortedOnAreaDecreasingOrder_ReturnSortedResult") {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv"
      ,"./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    val sortedCensusData = objCensus.getAreaWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state == "Rajasthan")
    assert(censusCSV.last.state == "Goa")
  }

  test("test_InputEmptyData_SortedOnAreaDecreasingOrder_RaisenoCensusDataException") {
    val objCensus = new CensusAnalyzer()
    val thrown = intercept[Exception] {
      val sortedCensusData = objCensus.getAreaWiseSortedCensusData()
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.noCensusData.toString)
  }

  test("test_GivenInput_USCensusFileWithRightPath_MatchingNumberOfRows_ReturnNumberOfRows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/USCensusData.csv") == 51)
  }

  test("test_GivenInput_USCensusData_WithInputPathIsWrong_RaiseIncorrectFilePathException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusData("./src/scala/CensusAnalyzerProject/Resources/USCensusData.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_GivenInput_USCensusData_WithInputFileTypeIsWrong_RaiseIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/USCensusData.txt")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_GivenInput_USCensusData_WithInputFileDelimiterIsWrong_RaiseUnableToParseException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/USCensusDataWrongDelimiter.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }

  test("test_GivenInput_USCensusData_WithInputFileFieldsIsWrong_RaiseUnableToParseException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCensusData("./src/main/scala/CensusAnalyzerProject/Resources/USCensusDataWrongFields.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.unableToParse.toString)
  }
}
