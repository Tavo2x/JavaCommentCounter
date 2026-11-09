public class JavaDocState implements State{
//- Handles char's when in a Java doc

//- The char '/' is recorded as a JavaDocComment
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordJavaDocComment('/');
    }

//- The char '\' is recorded as a JavaDocComment
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordJavaDocComment('\\');
    }
//- Switches to JavaDocStarState 
    @Override
    public void star(CommentCounter c) {
        c.setState(new JavaDocStarState());
    }

//- Records new line w the help of the newLine helper
    @Override
    public void newLine(CommentCounter c) {
        c.recordNewLine();
    }

//- The char '"' is recorded as a JavaDocComment
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordJavaDocComment('"');
    }

//- The char ''' is recorded as a JavaDocComment
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordJavaDocComment('\'');
    }

//- Anything else is recorded as a JavaDocComment
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordJavaDocComment(symbol);
    }

}
