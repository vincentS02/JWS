package fr.epita.assistants.presentation.rest.response;


public class ReverseResponse {
    public String original;
    public String reversed;

    public ReverseResponse(String original) {
        this.original = original;
        this.reversed = ReverseResponse(original);
    }

    public String ReverseResponse(String toreverse) {
        StringBuilder nouvellereq = new StringBuilder(toreverse);
        nouvellereq.reverse();
        return nouvellereq.toString();
    }
}