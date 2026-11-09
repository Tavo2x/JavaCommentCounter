public class StringState implements State {

    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordCode('/');
    }

    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordCode('\\');
        c.setState(new StringEscapeState());
    }

    @Override
    public void star(CommentCounter c) {
        c.recordCode('*');
    }

    @Override
    public void newLine(CommentCounter c) {
        c.recordNewLine();
    }

    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordCode('"');
        c.setState(new CodeState());
    }

    @Override
    public void singleQuote(CommentCounter c) {
        c.recordCode('\'');
    }

    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordCode(symbol);
    }

}
