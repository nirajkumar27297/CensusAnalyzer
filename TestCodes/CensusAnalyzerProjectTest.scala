import org.scalatest.FunSuite
import CensusAnalyzerProject.CensusAnalyzer

class CensusAnalyzerProjectTest extends FunSuite{
  test("test_MatchingNumberOfRows_Input_CSVFileWithRightPath_ReturnNumberOfrows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCSVData("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv") == 29)
  }
}
