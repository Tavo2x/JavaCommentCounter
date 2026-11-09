public class CodeState implements State{
//- Handles char's when in regualr code state

//- Transitions to ForwardSlashState to determine if its handling a comment or not
    @Override
    public void forwardSlash(CommentCounter c) {
        c.setState(new ForwardSlashState());
    }

//- Recorded as regular code
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordCode('\\');
    }

//- Recorded as regular code
    @Override
    public void star(CommentCounter c) {
        c.recordCode('*');
    }

//- Records new line w/ help of the newLine helper
    @Override
    public void newLine(CommentCounter c) {
        c.recordNewLine();
    }

//- Records as code
//- Swithces to StringState to begin reading a string
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordCode('"');
        c.setState(new StringState());
    }

//- Recrods as code
//- Switches to CharLiteralState to being reading a char
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordCode('\'');
        c.setState(new CharLiteralState());
    }

//- Records anything else as regular code
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordCode(symbol);
    }

}
