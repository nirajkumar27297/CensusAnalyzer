package CensusAnalyzerProject
import com.opencsv.bean.CsvBindByName

class IndiaStateCensusDTO {
  @CsvBindByName(required = true,column = "State")
  var state:String = null

  @CsvBindByName(required = true,column = "Population")
  var population:Double = 0

  @CsvBindByName(required = true,column = "AreaInSqKm")
  var areaInSqKm:Double = 0

  @CsvBindByName(required = true,column = "DensityPerSqKm")
  var densityPerSqKm:Double = 0

  override def toString: String = "IndiaCensusCSV{" +
    "State='" + state + '\'' +
    ", Population='" + population + '\'' +
    ", AreaInSqKm='" + areaInSqKm + '\'' +
    ", DensityPerSqKm='" + densityPerSqKm + '\'' +
    '}';
}