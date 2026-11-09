public class MultiLineCommentState implements State{
//- Handles char's when in a multi line comment

//- The char '/' is recorded as a MultiLineComment
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordMultiLineComment('/');
    }

//- The char '\' is recorded as a MultiLineComment
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordMultiLineComment('\\');
    }

//- Swithces state to MultiLineStarState
    @Override
    public void star(CommentCounter c) {
        c.setState(new MultiLineStarState());
    }

//- Records new line w/ help of the newLine helper
    @Override
    public void newLine(CommentCounter c) {
        c.recordNewLine();
    }

//- The char '"' is recorded as a MultiLineComment
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordMultiLineComment('"');
    }
//- The char ''' is recorded as a MultiLineComment
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordMultiLineComment('\'');
    }
//- Anything else is recorded as a multi line comment
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordMultiLineComment(symbol);
    }

}
