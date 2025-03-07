package chess;

import java.util.ArrayList;

class Queen extends Piece {

    public Queen(boolean isWhite, int rank, int file) {
        super(isWhite, rank, file);

        if (isWhite) {
            pieceType = PieceType.WQ;
        } else {
            pieceType = PieceType.BQ;
        }
    }

    boolean checkDir(Coord.Dir dir, Coord target, Board board) {
        Coord dst = currentCoord();
        while (true) {
            dst = dst.step(dir);
            if (dst != null && dst.equals(target)) {
                return true;
            }
            if (dst == null) {
                break;
            }
            if (board.getPiece(dst) != null) {
                break;
            }
        }
        return false;
    }

    @Override
    public void print() {
        if (isWhite) {
            System.out.print("wQ ");
        } else {
            System.out.print("bQ ");
        }
    }

    @Override
    public ReturnPlay.Message move(Board board, Coord dest) {
        // if we can move there we should move there : otherwise illegal
        // if we move there, and an opponent piece is there, we take it
        updatePosition(dest, board);
        return null;
    }

    @Override
    public boolean canTarget(Board board, Coord target, boolean attackOnly) {
        // can you get to the destination with valid moves
        // no jumps over pieces
        // can make it in one move
        Coord src = currentCoord();
        if (src.f == target.f) {
            // pieces on the same file (north / south)
            // walk until reach piece or end of board
            if (checkDir(Coord.Dir.N, target, board))
                return true;
            if (checkDir(Coord.Dir.S, target, board))
                return true;
            return false;

        } else if (src.r == target.r) {
            // pieces on the same rank (east / west)
            if (checkDir(Coord.Dir.E, target, board))
                return true;
            if (checkDir(Coord.Dir.W, target, board))
                return true;
            return false;
        } else {
            // maybe diagonal?
            if (checkDir(Coord.Dir.NE, target, board))
                return true;
            if (checkDir(Coord.Dir.NW, target, board))
                return true;
            if (checkDir(Coord.Dir.SE, target, board))
                return true;
            if (checkDir(Coord.Dir.SW, target, board))
                return true;
            return false;
        }
    }

    @Override
    public boolean canMove(Board board) {
        // is there any direction the queen can move 1 step or not?
        // use getPiece to check to see if there is a piece in the way. -> Once you find
        // a piece check if its yours or the opponents
        Coord src = currentCoord();
        if (checkMove(src.stepNorth(), board)) {
            return true;
        }

        if (checkMove(src.stepNorthEast(), board)) {
            return true;
        }

        if (checkMove(src.stepEast(), board)) {
            return true;
        }

        if (checkMove(src.stepSouthEast(), board)) {
            return true;
        }

        if (checkMove(src.stepSouth(), board)) {
            return true;
        }

        if (checkMove(src.stepSouthWest(), board)) {
            return true;
        }

        if (checkMove(src.stepWest(), board)) {
            return true;
        }

        if (checkMove(src.stepNorthWest(), board)) {
            return true;
        }

        return false;
    }

}
