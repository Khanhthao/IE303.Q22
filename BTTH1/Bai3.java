import java.util.*;

class Point implements Comparable<Point> {
    double x, y;
    Point(double x, double y) { this.x = x; this.y = y; }

    public int compareTo(Point p) {
        return this.x != p.x ? Double.compare(this.x, p.x) : Double.compare(this.y, p.y);
    }
}

public class Bai3 {
    private static double cross(Point a, Point b, Point c) {
        return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
    }

    public static List<Point> convexHull(List<Point> points) {
        Collections.sort(points);
        List<Point> hull = new ArrayList<>();

        // Lower hull
        for (Point p : points) {
            while (hull.size() >= 2 && cross(hull.get(hull.size()-2), hull.get(hull.size()-1), p) <= 0)
                hull.remove(hull.size()-1);
            hull.add(p);
        }

        // Upper hull
        int lowerSize = hull.size();
        for (int i = points.size() - 2; i >= 0; i--) {
            Point p = points.get(i);
            while (hull.size() > lowerSize && cross(hull.get(hull.size()-2), hull.get(hull.size()-1), p) <= 0)
                hull.remove(hull.size()-1);
            hull.add(p);
        }

        hull.remove(hull.size()-1); // bỏ trùng
        return hull;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số lượng trạm: ");
        int n = sc.nextInt();

        List<Point> stations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập tọa độ trạm " + (i+1) + " (x y): ");
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            stations.add(new Point(x, y));
        }

        List<Point> warningStations = convexHull(stations);

        System.out.println("Các trạm cảnh báo (tọa độ biên):");
        for (Point p : warningStations) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
        sc.close();
    }
}
