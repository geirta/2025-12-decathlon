package ee.geir.decathlon.dto;

public class ResultRequest {

    public String competitorName;
    public String categoryName;
    public double result;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCompetitorName() {
        return competitorName;
    }

    public void setCompetitorName(String competitorName) {
        this.competitorName = competitorName;
    }

    public ResultRequest() {}

    public ResultRequest(String competitorName, String categoryName, double result) {
        this.competitorName = competitorName;
        this.categoryName = categoryName;
        this.result = result;
    }

}


