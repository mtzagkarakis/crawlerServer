package gr.aueb.mscis.productCrawlerServer.stringSimilarity;

import org.junit.Test;

import info.debatty.java.stringsimilarity.CharacterSubstitutionInterface;
import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.Jaccard;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.LongestCommonSubsequence;
import info.debatty.java.stringsimilarity.NGram;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import info.debatty.java.stringsimilarity.OptimalStringAlignment;
import info.debatty.java.stringsimilarity.QGram;
import info.debatty.java.stringsimilarity.SorensenDice;
import info.debatty.java.stringsimilarity.WeightedLevenshtein;
import info.debatty.java.stringsimilarity.experimental.Sift4;

public class StringSimilarityTest {
	String product_title_1 = "samsung galaxy s7 edge";
    String product_title_2 = "θήκη samsung book cover για galaxy s7 χρυσή";
    String product_title_3 = "samsung galaxy s7 4g+ smartphone μαύρο";
    String product_title_4 = "samsung galaxy s7 edge (32gb)";
    String product_title_5 = "samsung galaxy s7 duos (64gb)";
    String product_title_6 = "κινητό samsung galaxy s7 edge (64gb)";
    
    
   /* @Test
    public void similaritySIFT4(){
        Sift4 sift4 = new Sift4();
        sift4.setMaxOffset(5);

        System.out.println("----- SIFT4 -----");
        System.out.println("----- distance -----");
        System.out.println(sift4.distance(product_title_1, product_title_2));
        System.out.println(sift4.distance(product_title_1, product_title_3));
        System.out.println(sift4.distance(product_title_1, product_title_4));
        System.out.println(sift4.distance(product_title_1, product_title_5));
        System.out.println(sift4.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    @Test
    public void similarityJaccard2(){
        Jaccard jaccard2 = new Jaccard(2);
        System.out.println("----- Jaccard -----");
        System.out.println("----- 2-Grams -----");
        System.out.println(jaccard2.similarity(product_title_1, product_title_2));
        System.out.println(jaccard2.similarity(product_title_1, product_title_3));
        System.out.println(jaccard2.similarity(product_title_1, product_title_4));
        System.out.println(jaccard2.similarity(product_title_1, product_title_5));
        System.out.println(jaccard2.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    @Test
    public void similarityJaccard3(){
        Jaccard jaccard3 = new Jaccard(3);
        System.out.println("----- Jaccard -----");
        System.out.println("----- 3-Grams -----");
        System.out.println(jaccard3.similarity(product_title_1, product_title_2));
        System.out.println(jaccard3.similarity(product_title_1, product_title_3));
        System.out.println(jaccard3.similarity(product_title_1, product_title_4));
        System.out.println(jaccard3.similarity(product_title_1, product_title_5));
        System.out.println(jaccard3.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    @Test
    public void similarityJaccard4(){
        Jaccard jaccard4 = new Jaccard(4);
        System.out.println("----- Jaccard -----");
        System.out.println("----- 4-Grams -----");
        System.out.println(jaccard4.similarity(product_title_1, product_title_2));
        System.out.println(jaccard4.similarity(product_title_1, product_title_3));
        System.out.println(jaccard4.similarity(product_title_1, product_title_4));
        System.out.println(jaccard4.similarity(product_title_1, product_title_5));
        System.out.println(jaccard4.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    /*@Test
    public void similarityJaroWinkler(){
        JaroWinkler jarowinkler = new JaroWinkler();
        System.out.println("--- JaroWinkler ---");
        System.out.println(jarowinkler.similarity(product_title_1, product_title_2));
        System.out.println(jarowinkler.similarity(product_title_1, product_title_3));
        System.out.println(jarowinkler.similarity(product_title_1, product_title_4));
        System.out.println(jarowinkler.similarity(product_title_1, product_title_5));
        System.out.println(jarowinkler.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    @Test
    public void similarityCosine2(){
        Cosine cosine2 = new Cosine(2);
        System.out.println("----  Cosine 2 ----");
        System.out.println(cosine2.similarity(product_title_1, product_title_2));
        System.out.println(cosine2.similarity(product_title_1, product_title_3));
        System.out.println(cosine2.similarity(product_title_1, product_title_4));
        System.out.println(cosine2.similarity(product_title_1, product_title_5));
        System.out.println(cosine2.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    @Test
    public void similarityCosine3(){
        Cosine cosine3 = new Cosine(3);
        System.out.println("----  Cosine 3 ----");
        System.out.println(cosine3.similarity(product_title_1, product_title_2));
        System.out.println(cosine3.similarity(product_title_1, product_title_3));
        System.out.println(cosine3.similarity(product_title_1, product_title_4));
        System.out.println(cosine3.similarity(product_title_1, product_title_5));
        System.out.println(cosine3.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    @Test
    public void similarityCosine4(){
        Cosine cosine4 = new Cosine(4);
        System.out.println("----  Cosine 4 ----");
        System.out.println(cosine4.similarity(product_title_1, product_title_2));
        System.out.println(cosine4.similarity(product_title_1, product_title_3));
        System.out.println(cosine4.similarity(product_title_1, product_title_4));
        System.out.println(cosine4.similarity(product_title_1, product_title_5));
        System.out.println(cosine4.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    /*@Test
    public void similarityNGram2(){
        NGram ngram2 = new NGram(2);
        System.out.println("----- NGram 2 -----");
        System.out.println(ngram2.distance(product_title_1, product_title_2));
        System.out.println(ngram2.distance(product_title_1, product_title_3));
        System.out.println(ngram2.distance(product_title_1, product_title_4));
        System.out.println(ngram2.distance(product_title_1, product_title_5));
        System.out.println(ngram2.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    @Test
    public void similarityNGram3(){
        NGram ngram3 = new NGram(3);
        System.out.println("----- NGram 3 -----");
        System.out.println(ngram3.distance(product_title_1, product_title_2));
        System.out.println(ngram3.distance(product_title_1, product_title_3));
        System.out.println(ngram3.distance(product_title_1, product_title_4));
        System.out.println(ngram3.distance(product_title_1, product_title_5));
        System.out.println(ngram3.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    @Test
    public void similarityNGram4(){
        NGram ngram4 = new NGram(4);
        System.out.println("----- NGram 4 -----");
        System.out.println(ngram4.distance(product_title_1, product_title_2));
        System.out.println(ngram4.distance(product_title_1, product_title_3));
        System.out.println(ngram4.distance(product_title_1, product_title_4));
        System.out.println(ngram4.distance(product_title_1, product_title_5));
        System.out.println(ngram4.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    
    /*@Test
    public void similarityNormalizedLevenshtein(){
        NormalizedLevenshtein normalLevenshtein = new NormalizedLevenshtein();
        System.out.println("-- NLevenshtein  --");
        System.out.println(normalLevenshtein.distance(product_title_1, product_title_2));
        System.out.println(normalLevenshtein.distance(product_title_1, product_title_3));
        System.out.println(normalLevenshtein.distance(product_title_1, product_title_4));
        System.out.println(normalLevenshtein.distance(product_title_1, product_title_5));
        System.out.println(normalLevenshtein.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    
    
    
    @Test
    public void similaritySorcensendice2(){
        SorensenDice sorcensendice2 = new SorensenDice(2);
        System.out.println("- Sorensen Dice 2 -");
        System.out.println(sorcensendice2.similarity(product_title_1, product_title_2));
        System.out.println(sorcensendice2.similarity(product_title_1, product_title_3));
        System.out.println(sorcensendice2.similarity(product_title_1, product_title_4));
        System.out.println(sorcensendice2.similarity(product_title_1, product_title_5));
        System.out.println(sorcensendice2.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    @Test
    public void similaritySorcensendice3(){
        SorensenDice sorcensendice3 = new SorensenDice(3);
        System.out.println("- Sorensen Dice 3 -");
        System.out.println(sorcensendice3.similarity(product_title_1, product_title_2));
        System.out.println(sorcensendice3.similarity(product_title_1, product_title_3));
        System.out.println(sorcensendice3.similarity(product_title_1, product_title_4));
        System.out.println(sorcensendice3.similarity(product_title_1, product_title_5));
        System.out.println(sorcensendice3.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    @Test
    public void similaritySorcensendice4(){
        SorensenDice sorcensendice4 = new SorensenDice(4);
        System.out.println("- Sorensen Dice 4 -");
        System.out.println(sorcensendice4.similarity(product_title_1, product_title_2));
        System.out.println(sorcensendice4.similarity(product_title_1, product_title_3));
        System.out.println(sorcensendice4.similarity(product_title_1, product_title_4));
        System.out.println(sorcensendice4.similarity(product_title_1, product_title_5));
        System.out.println(sorcensendice4.similarity(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    /*@Test
    public void similarityLevenshtein(){
    	Levenshtein levenshtein = new Levenshtein();
        System.out.println("--- Levenshtein ---");
        System.out.println(levenshtein.distance(product_title_1, product_title_2));
        System.out.println(levenshtein.distance(product_title_1, product_title_3));
        System.out.println(levenshtein.distance(product_title_1, product_title_4));
        System.out.println(levenshtein.distance(product_title_1, product_title_5));
        System.out.println(levenshtein.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    /*@Test
    public void similarityWeightedLevenshtein(){
        WeightedLevenshtein wl1 = new WeightedLevenshtein(
                new CharacterSubstitutionInterface() {
                    public double cost(char c1, char c2) {
                        // The cost for substituting 't' and 'r' is considered
                        // smaller as these 2 are located next to each other
                        // on a keyboard
                        if (c1 == 't' && c2 == 'r')
                            return 0.5;
                        // For most cases, the cost of substituting 2 characters
                        // is 1.0
                        return 0.5;
                    }
                });
        System.out.println("- Weighted Lev 1  -");
        System.out.println(wl1.distance(product_title_1, product_title_2));
        System.out.println(wl1.distance(product_title_1, product_title_3));
        System.out.println(wl1.distance(product_title_1, product_title_4));
        System.out.println(wl1.distance(product_title_1, product_title_5));
        System.out.println(wl1.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    /*@Test
    public void similarityWeightedLevenshtein2(){
        WeightedLevenshtein wl2 = new WeightedLevenshtein(
                new CharacterSubstitutionInterface() {
                    public double cost(char c1, char c2) {
                        // The cost for substituting 't' and 'r' is considered
                        // smaller as these 2 are located next to each other
                        // on a keyboard
                        if (c1 == 't' && c2 == 'r')
                            return 0.4;
                        // For most cases, the cost of substituting 2 characters
                        // is 1.0
                        return 0.6;
                    }
                });
        System.out.println("- Weighted Lev 2  -");
        System.out.println(wl2.distance(product_title_1, product_title_2));
        System.out.println(wl2.distance(product_title_1, product_title_3));
        System.out.println(wl2.distance(product_title_1, product_title_4));
        System.out.println(wl2.distance(product_title_1, product_title_5));
        System.out.println(wl2.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    /*@Test
    public void similarityWeightedLevenshtein3(){
        WeightedLevenshtein wl3 = new WeightedLevenshtein(
                new CharacterSubstitutionInterface() {
                    public double cost(char c1, char c2) {
                        // The cost for substituting 't' and 'r' is considered
                        // smaller as these 2 are located next to each other
                        // on a keyboard
                        if (c1 == 't' && c2 == 'r')
                            return 0.25;
                        // For most cases, the cost of substituting 2 characters
                        // is 1.0
                        return 0.75;
                    }
                });
        System.out.println("- Weighted Lev 3  -");
        System.out.println(wl3.distance(product_title_1, product_title_2));
        System.out.println(wl3.distance(product_title_1, product_title_3));
        System.out.println(wl3.distance(product_title_1, product_title_4));
        System.out.println(wl3.distance(product_title_1, product_title_5));
        System.out.println(wl3.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    /*@Test
    public void similarityDamerau(){
        Damerau damerau = new Damerau();
        System.out.println("----  Damerau  ----");
        System.out.println(damerau.distance(product_title_1, product_title_2));
        System.out.println(damerau.distance(product_title_1, product_title_3));
        System.out.println(damerau.distance(product_title_1, product_title_4));
        System.out.println(damerau.distance(product_title_1, product_title_5));
        System.out.println(damerau.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    
   /* @Test
    public void similarityOptimalAlign(){
        OptimalStringAlignment osa = new OptimalStringAlignment();
        System.out.println("-- Optimal Align --");
        System.out.println(osa.distance(product_title_1, product_title_2));
        System.out.println(osa.distance(product_title_1, product_title_3));
        System.out.println(osa.distance(product_title_1, product_title_4));
        System.out.println(osa.distance(product_title_1, product_title_5));
        System.out.println(osa.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    @Test
    public void similarityLongestCommonSubsequence(){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println("-- Longest Dist  --");
        System.out.println(lcs.distance(product_title_1, product_title_2));
        System.out.println(lcs.distance(product_title_1, product_title_3));
        System.out.println(lcs.distance(product_title_1, product_title_4));
        System.out.println(lcs.distance(product_title_1, product_title_5));
        System.out.println(lcs.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    /*@Test
    public void similarityQGram2(){
        QGram qgram2 = new QGram(2);
        System.out.println("----- QGram 2 -----");
        System.out.println(qgram2.distance(product_title_1, product_title_2));
        System.out.println(qgram2.distance(product_title_1, product_title_3));
        System.out.println(qgram2.distance(product_title_1, product_title_4));
        System.out.println(qgram2.distance(product_title_1, product_title_5));
        System.out.println(qgram2.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    @Test
    public void similarityQGram3(){
        QGram qgram3 = new QGram(3);
        System.out.println("----- QGram 3 -----");
        System.out.println(qgram3.distance(product_title_1, product_title_2));
        System.out.println(qgram3.distance(product_title_1, product_title_3));
        System.out.println(qgram3.distance(product_title_1, product_title_4));
        System.out.println(qgram3.distance(product_title_1, product_title_5));
        System.out.println(qgram3.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }
    
    @Test
    public void similarityQGram4(){
        QGram qgram4 = new QGram(4);
        System.out.println("----- QGram 4 -----");
        System.out.println(qgram4.distance(product_title_1, product_title_2));
        System.out.println(qgram4.distance(product_title_1, product_title_3));
        System.out.println(qgram4.distance(product_title_1, product_title_4));
        System.out.println(qgram4.distance(product_title_1, product_title_5));
        System.out.println(qgram4.distance(product_title_1, product_title_6));
        System.out.println("-------------------");
    }*/
    
}
