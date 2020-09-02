package CensusAnalyzerProject
import com.opencsv.bean.CsvBindByName
class IndianStateCodeDTO {

  @CsvBindByName(required = true,column = "State Name")
  val stateName:String = null

  @CsvBindByName(required = true,column = "StateCode")
  val stateCode:String = null

  override def toString: String = "IndiaCensusCSV{" +
    ", StateName='" + stateName + '\'' +
    ", StateCode='" + stateCode + '\'' +
    '}'
}
