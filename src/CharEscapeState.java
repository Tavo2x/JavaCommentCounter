public class CharEscapeState implements State{

    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordCode('/');
        c.setState(new CharLiteralState());
    }

    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordCode('\\');
        c.setState(new CharLiteralState());
    }

    @Override
    public void star(CommentCounter c) {
        c.recordCode('*');
        c.setState(new CharLiteralState());
    }

    @Override
    public void newLine(CommentCounter c) {
        c.recordNewLine();
        c.setState(new CharLiteralState());
    }

    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordCode('"');
        c.setState(new CharLiteralState());
    }

    @Override
    public void singleQuote(CommentCounter c) {
        c.recordCode('\'');
        c.setState(new CharLiteralState());
    }

    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordCode(symbol);
        c.setState(new CharLiteralState());
    }
    
}
