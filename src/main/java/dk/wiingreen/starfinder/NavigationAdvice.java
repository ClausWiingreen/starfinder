package dk.wiingreen.starfinder;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class NavigationAdvice {
  private final List<NavigationItem> navigationItems =
      List.of(
          new NavigationItem("Characters", "/characters", false),
          new NavigationItem("Campaigns", "/campaigns", false));

  @ModelAttribute("navigationItems")
  List<NavigationItem> getNavigationItems(HttpServletRequest request) {
    return navigationItems.stream()
        .map(
            navigationItem ->
                navigationItem.withActive(request.getRequestURI().startsWith(navigationItem.url)))
        .toList();
  }

  public record NavigationItem(String title, String url, boolean active) {
    public NavigationItem withActive(boolean active) {
      return new NavigationItem(title, url, active);
    }
  }
}
