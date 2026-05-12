// Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules. Both should depend on abstractions. Additionally, abstractions should not depend on details. Details should depend on abstractions. This principle helps to reduce the coupling between different parts of the code and promotes a more flexible and maintainable codebase.

// Bad Example
class TrendingRecommendation {
    public void recommend() {
        System.out.println("Recommending products...");
    }
}

class GenreRecommendation {
    public void recommend() {
        System.out.println("Recommending products based on genre...");
    }
}

class RecentRecommendation {
    public void recentRecommend() {
        System.out.println("Recommending recently viewed products...");
    }
}

// Good example
interface RecommendationStrategy {
    void recommend();
}

class NewTrendingRecommendation implements RecommendationStrategy {
    @Override
    public void recommend() {
        System.out.println("Recommending products...");
    }
}

class NewGenreRecommendation implements RecommendationStrategy {
    @Override
    public void recommend() {
        System.out.println("Recommending products based on genre...");
    }
}

class NewRecentRecommendation implements RecommendationStrategy {
    @Override
    public void recommend() {
        System.out.println("Recommending recently viewed products...");
    }
}

// OR

class RecommendationService {
    private RecommendationStrategy recommendationStrategy;

    public RecommendationService(RecommendationStrategy recommendationStrategy) {
        this.recommendationStrategy = recommendationStrategy;
    }

    public void recommend() {
        recommendationStrategy.recommend();
    }
}

public class Main {
    public static void main(String[] args) {
        // Bad example
        TrendingRecommendation trendingRecommendation = new TrendingRecommendation();
        trendingRecommendation.recommend(); 
        GenreRecommendation genreRecommendation = new GenreRecommendation();
        genreRecommendation.recommend();
        RecentRecommendation recentRecommendation = new RecentRecommendation();
        recentRecommendation.recentRecommend(); // This is not ideal as we have to create multiple instances of different recommendation classes and call their respective methods.

        // Good example
        RecommendationService recommendationService = new RecommendationService(new NewTrendingRecommendation());
        recommendationService.recommend(); // This is better as we can easily switch between different recommendation strategies without changing the client code. We can also easily add new recommendation strategies by implementing the RecommendationStrategy interface without modifying the existing code.
    }    
}
