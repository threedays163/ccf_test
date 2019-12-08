package ccf._2013._5;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        //solve_1();
        new Main().run();
    }

    public void run() {
        Scanner fin = new Scanner(System.in);
        int R = fin.nextInt();
        int C = fin.nextInt();
        fin.nextLine();
        int[][] board = new int[R + 2][C + 2];
        int rowStart = 0, colStart = 0, rowEnd = 0, colEnd = 0;
        //初始化数组, 并给board赋权值(i=[1,5],j[1,5])
        for (int i = 1; i <= R; ++i) {
            String line = fin.nextLine();
            for (int j = 1; j <= C; ++j) {
                char c = line.charAt(j - 1);
                switch (c) {
                    case '#':
                        break;
                    case '-':
                        board[i][j] = 5;
                        break;
                    case '|':
                        board[i][j] = 0xA;
                        break;
                    case '+':
                    case 'S':
                    case 'T':
                        board[i][j] = 0xF;
                        break;
                    case '.':
                        board[i][j] = 0x8;
                        break;
                    default:
                        break;
                }
                if (c == 'S') {
                    rowStart = i;
                    colStart = j;
                } else if (c == 'T') {
                    rowEnd = i;
                    colEnd = j;
                }
            }
        }
        //表示方向组合, 分别表示向右r=0,c=1(0,1), 向上(-1,0), 向左(0,-1), 向下(1,0)  及分别代表 坐标轴四个方向, 逆时针方向枚举
        int[] dr = new int[]{0, -1, 0, 1};
        int[] dc = new int[]{1, 0, -1, 0};
        // Scan 1: find all cells which can reach T

        boolean[][] visited = new boolean[R + 2][C + 2];
        boolean[][] canReachT = new boolean[R + 2][C + 2];
        //边框都是true,内部都是false
        initVisited(visited);
        //终点可到达,终点已访问
        canReachT[rowEnd][colEnd] = true;
        visited[rowEnd][colEnd] = true;
        Queue<Integer> queue = new LinkedList<Integer>();
        //每次放进去两个
        queue.add(rowEnd);
        queue.add(colEnd);
        while (!queue.isEmpty()) {
            //每次取出来两个
            int r = queue.remove();
            int c = queue.remove();
            //分别枚举可达节点的四个方向(右,上,左,下)
            for (int i = 0; i < 4; ++i) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                //已访问过,继续枚举其他方向
                if (visited[nr][nc]) continue;

                //由于最开始的转向数组的顺序是: 0123:右上左下 i+2后对4求余表示:方向顺序变成2301, 即左下右上
                //然后由 1<< (2,3,0,1)位,分别得到: 4,8,1,2 即4个2的整数幂       (注意左移运算 a<<b运算是指 左边的数a左移b位, 最开始理解错了,理解成b左移a位了)
                if ((board[nr][nc] & (1 << ((i + 2) % 4))) != 0) {
                    canReachT[nr][nc] = true;
                    queue.add(nr);
                    queue.add(nc);
                    visited[nr][nc] = true;
                }
            }
        }
 /*
 for (int i = 1; i <= R; ++i) {
 for (int j = 1; j <= C; ++j) {
 if (canReachT[i][j]) {
 System.out.println("i = " + i + ", j = " + j);
 }
 }
 }
 */
        //起始点不能到达终点,输出: I`m stuck
        if (!canReachT[rowStart][colStart]) {
            System.out.println("I'm stuck!");
            return;
        }
        // Scan 2: get result
        boolean[][] rCanReach = new boolean[R + 2][C + 2];
        initVisited(visited);
        queue.clear();
        visited[rowStart][colStart] = true;
        rCanReach[rowStart][colStart] = true;
        queue.add(rowStart);
        queue.add(colStart);
        while (!queue.isEmpty()) {
            int r = queue.remove();
            int c = queue.remove();
            for (int i = 0; i < 4; ++i) {
                if ((board[r][c] & (1 << i)) == 0) continue;
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (visited[nr][nc]) continue;
                if (board[nr][nc] == 0) continue;
                rCanReach[nr][nc] = true;
                queue.add(nr);
                queue.add(nc);
                visited[nr][nc] = true;
            }
        }
        int result = 0;
        for (int i = 1; i <= R; ++i) {
            for (int j = 1; j <= C; ++j) {
 /*
 if (rCanReach[i][j]) {
 System.out.println("i = " + i + ", j = " + j);
 }
 */
                if (rCanReach[i][j] && (!canReachT[i][j])) ++result;
            }
        }
        System.out.println(result);
    }

    private void initVisited(boolean[][] visited) {
        int R = visited.length - 2;
        int C = visited[0].length - 2;
        for (int i = 0; i <= R + 1; ++i) {
            visited[i][0] = true;
            visited[i][C + 1] = true;
        }
        for (int j = 0; j <= C + 1; ++j) {
            visited[0][j] = true;
            visited[R + 1][j] = true;
        }
        for (int i = 1; i <= R; ++i) {
            for (int j = 1; j <= C; ++j) {
                visited[i][j] = false;

            }

        }

    }

    static class Point {
        int x;
        int y;

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int R;
    static int C;


    public static void solve_1() {
        Scanner s = new Scanner(System.in);

        R = s.nextInt();
        C = s.nextInt();

        s.nextLine();

        char[][] a = new char[R][C];

        //坐标是否可达 0未判断 1:可达其他节点 2:终止
        int[][] tem = new int[R][C];

        int startX = -1, startY = -1;

        for (int i = 0; i < R; i++) {
            String string = s.nextLine();
            for (int j = 0; j < C; j++) {
                a[i][j] = string.charAt(j);
                tem[i][j] = 0;
                if (a[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        Set<Point> set = new HashSet<>();

        LinkedBlockingQueue<Point> needSearch = new LinkedBlockingQueue<>();
        needSearch.offer(new Point(startX, startY));
        while (needSearch.peek() != null) {
            Point p = needSearch.poll();
            if (tem[p.x][p.y] == 1) {
                continue;
            }
            boolean isValid = false;
            switch (a[p.x][p.y]) {
                case 'S':
                    isValid = check4IsValid(a, p.x, p.y, tem, needSearch);
                    if (isValid == false) {
                        System.out.println("I'm stuck!");
                        System.exit(0);
                    }
                case '+':
                case 'T':
                    isValid = check4IsValid(a, p.x, p.y, tem, needSearch);
                    break;
                case '-':
                case '|':
                    isValid = check2IsValid(a, p.x, p.y, tem, needSearch);
                    break;
                case '.':
                    isValid = checkDownIsValid(a, p.x, p.y, tem, needSearch);
                    break;
                default:
                    break;
            }
            tem[p.x][p.y] = 1;
            if (isValid == false) {
                set.add(new Point(p.x, p.y));
            }
        }
        System.out.println(set.size());
    }

    public static boolean check4IsValid(char[][] a, int x, int y, int[][] tem, LinkedBlockingQueue<Point> needSearch) {
        if (a[x][y] == '#') {
            return false;
        }
        int count = 0;
        if (x - 1 >= 0 && a[x - 1][y] != '#') {
            if (tem[x - 1][y] == 0) {
                needSearch.offer(new Point(x - 1, y));
            }
            count++;
        }
        if (y + 1 < C && a[x][y + 1] != '#') {
            if (tem[x][y + 1] == 0) {
                needSearch.offer(new Point(x, y + 1));
            }
            count++;
        }

        if (x + 1 < R && a[x + 1][y] != '#') {
            if (tem[x + 1][y] == 0) {
                needSearch.offer(new Point(x + 1, y));
            }
            count++;
        }

        if (y - 1 >= 0 && a[x][y - 1] != '#') {
            if (tem[x][y - 1] == 0) {
                needSearch.offer(new Point(x, y - 1));
            }
            count++;
        }
        return count > 0 ? true : false;
    }

    public static boolean check2IsValid(char[][] a, int x, int y, int[][] tem, LinkedBlockingQueue<Point> needSearch) {
        if (a[x][y] == '#') {
            return false;
        }
        int count = 0;
        if (a[x][y] == '-') {
            if (y + 1 < C && a[x][y + 1] != '#') {
                if (tem[x][y + 1] == 0) {
                    needSearch.offer(new Point(x, y + 1));
                }
                count++;
            }
            if (y - 1 >= 0 && a[x][y - 1] != '#') {
                if (tem[x][y - 1] == 0) {
                    needSearch.offer(new Point(x, y - 1));
                }
                count++;
            }

        } else if (a[x][y] == '|') {
            if (x - 1 >= 0 && a[x - 1][y] != '#') {
                if (tem[x - 1][y] == 0) {
                    needSearch.offer(new Point(x - 1, y));
                }
                count++;
            }
            if (x + 1 < R && a[x + 1][y] != '#') {
                if (tem[x + 1][y] == 0) {
                    needSearch.offer(new Point(x + 1, y));
                }
                count++;
            }
        }
        return count > 0 ? true : false;
    }

    public static boolean checkDownIsValid(char[][] a, int x, int y, int[][] tem, LinkedBlockingQueue<Point> needSearch) {
        if (a[x][y] == '#') {
            return false;
        }
        int count = 0;
        if (a[x][y] == '.') {
            if (x + 1 < R && a[x + 1][y] != '#') {
                if (tem[x + 1][y] == 0) {
                    needSearch.offer(new Point(x + 1, y));
                }
                count++;
            }
        }
        return count > 0 ? true : false;
    }
}
