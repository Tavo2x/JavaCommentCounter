public class ForwardSlashState implements State{
//- Intended to handle the '/', to determine if it was part of a comment or code

//- Records single line comment
//- Then switches to the SingleLineCommentState
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordSingleLineComment('/');
        c.recordSingleLineComment('/');
        c.setState(new SingleLineCommentState());
    }

//- Records '/' and '\' as regular code
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordCode('/');
        c.recordCode('\\');
        c.setState(new CodeState());
    }

//- Switches state to StarState to check for block comment
    @Override
    public void star(CommentCounter c) {
        c.setState(new StarState());
    }

//- Treats '/' as code 
//- Handles new line with newline helper
    @Override
    public void newLine(CommentCounter c) {
        c.recordCode('/');
        c.recordNewLine();
        c.setState(new CodeState());
    }

//- Records as code
//- Switches to StringState
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordCode('/');    
        c.recordCode('"');
        c.setState(new StringState());   
    }

//- Logs as code
//- Switches to CharLiteralState
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordCode('/');    
        c.recordCode('\'');
        c.setState(new CharLiteralState());
    }

//- Records both as code and switches to CodeState
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordCode('/');
        c.recordCode(symbol);
        c.setState(new CodeState());
    }

}
