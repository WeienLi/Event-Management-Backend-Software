package src;

public interface Visitor {
    void visitFilterResult(FilterResult pfilter);
    void visitConcert(Concert pconcert);
    void visitGala(Gala pgala);
    void visitWorkshop(Workshops pwork);
    void visitScreening(Screening pscreen);
    void visitFestival(Festival pfest);
}
