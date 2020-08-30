import org.scalatest.FunSuite
import CensusAnalyzerProject.CensusAnalyzer
import CensusAnalyzerProject.CensusAnalyzerExceptionEnum

class CensusAnalyzerProjectTest extends FunSuite{
  test("test_MatchingNumberOfRows_Input_CSVFileWithRightPath_ReturnNumberOfrows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCSVData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv") == 29)
  }

  test("test_InputFilePathIsWrong_ReturnIncorrectFilePathException") {
    val thrown = intercept[Exception] {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCSVData("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_InputFileTypeIsWrong_ReturnIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      assert(objCensus.loadCSVData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.txt") == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
    }
  }
}
