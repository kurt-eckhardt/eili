package eili.scratch;

import java.util.*;

public class BoxMaze {

    public static void main(String[] args) {
        char[][] maze = {
                {'#', '#','#',' ','#'},
                {' ', ' ','B',' ','#'},
                {'#', ' ',' ',' ',' '},
                {'#', ' ',' ',' ',' '},
                {'#', '#','#','#','T'}
        };

        ArrayDeque<Cell> path = pathToTarget(maze, new Cell(2,1), new Cell(4,4));
        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }


    public static ArrayDeque<Cell> pathToTarget(char[][] maze, Cell box, Cell target) {

        Map<Cell, Cell> visited  = new HashMap<>(); // visited, from cell (for backtracking)
        ArrayDeque<Cell> toVisit = new ArrayDeque<>();
        toVisit.add(box);
        visited.put(box, null);
        
        while (!toVisit.isEmpty()) {
            Cell visiting = toVisit.remove();
            System.out.println("Visiting: " + visiting);
            if (foundTarget(visiting, maze)) {
                return buildPathToTarget(visiting, visited);
            } else {
                for (Cell legalMove : getLegalMoves(visiting, maze)) {
                    if (!visited.containsKey(legalMove)) {
                        visited.put(legalMove, visiting);
                        toVisit.add(legalMove);
                    }
                }
            }
        }

        return new ArrayDeque<Cell>();
    }


    public static ArrayDeque<Cell> buildPathToTarget(Cell visiting, Map<Cell, Cell> visited) {
        ArrayDeque<Cell> path = new ArrayDeque<>();
        while (visiting != null) {
            path.push(visiting);
            visiting = visited.get(visiting);
        }
        return path;
    }


    public static List<Cell> getLegalMoves(Cell atCell, char[][] maze) {

        List<Cell> moves = new ArrayList<>();
        if (isOpenLeft(atCell, maze) && isOpenRight(atCell, maze)) {
            moves.add(new Cell(atCell.x-1, atCell.y));
            moves.add(new Cell(atCell.x+1, atCell.y));
        }
        
        if (isOpenAbove(atCell, maze) && isOpenBelow(atCell, maze)) {
            moves.add(new Cell(atCell.x, atCell.y-1));
            moves.add(new Cell(atCell.x, atCell.y+1));
        }
        
        return moves;
    }
    
    public static boolean isOpenLeft(Cell atCell, char[][] maze) {
        return atCell.x > 0 && maze[atCell.x-1][atCell.y] != '#';
    }

    public static boolean isOpenRight(Cell atCell, char[][] maze) {
        return atCell.x < maze.length-1 && maze[atCell.x+1][atCell.y] != '#';
    }

    public static boolean isOpenAbove(Cell atCell, char[][] maze) {
        return atCell.y > 0 && maze[atCell.x][atCell.y-1] != '#';
    }

    public static boolean isOpenBelow(Cell atCell, char[][] maze) {
        return atCell.y < maze[0].length-1 && maze[atCell.x][atCell.y+1] != '#';
    }

    // override equals and hashcode
    public static class Cell {
        int x, y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Cell) {
                Cell otherCell = (Cell)other;
                return otherCell.x == this.x && otherCell.y == this.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 7 * this.x * 31 * this.y;
        }

        @Override
        public String toString() {
            return "x="+this.x + " y="+this.y;
        }
    }

    public static boolean foundTarget(Cell visiting, char[][] maze) {
        return maze[visiting.x][visiting.y] == 'T';
    }
}
