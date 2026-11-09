public class StringEscapeState implements State{

    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordCode('/');
        c.setState(new StringState());
    }

    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordCode('\\');
        c.setState(new StringState());
    }

    @Override
    public void star(CommentCounter c) {
        c.recordCode('*');
        c.setState(new StringState());
    }

    @Override
    public void newLine(CommentCounter c) {
        c.recordNewLine();
        c.setState(new StringState());
    }

    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordCode('"');
        c.setState(new StringState());
    }

    @Override
    public void singleQuote(CommentCounter c) {
        c.recordCode('\'');
        c.setState(new StringState());
    }

    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordCode(symbol);
        c.setState(new StringState());
    }

}
