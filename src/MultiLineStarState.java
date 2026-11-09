public class MultiLineStarState implements State{
//- Handles char's after seeing a '*' insides a multi line comment

//- Records both ending char's as part of a MultiLineComment
//- Switches state to CodeState since the comment is finished
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('/');
        c.setState(new CodeState());
    }

//- Records '*' and '\' as part of the MultiLineComment
//- Switches state to MultiLineCommentState since the comment has not ended
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('\\');
        c.setState(new MultiLineCommentState());
    }

//- Records '*' as part of the MultiLineComment
    @Override
    public void star(CommentCounter c) {
        c.recordMultiLineComment('*');
    }

//- Records the '*' and the new line as MultiLineComment
//- Swithces to MutliLineCommentState
    @Override
    public void newLine(CommentCounter c) {
        c.recordMultiLineComment('*');
        c.recordNewLine();
        c.setState(new MultiLineCommentState());
    }

//- Records the '*' and the '"' as MultiLineComment
//- Swithces to MutliLineCommentState
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('"');
        c.setState(new MultiLineCommentState());
    }

//- Records the '*' and the ''' as MultiLineComment
//- Swithces to MutliLineCommentState
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('\'');
        c.setState(new MultiLineCommentState());
    }    

//- Records both as part of the MultiLineComment
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordMultiLineComment('*');
        c.recordMultiLineComment(symbol);
        c.setState(new MultiLineCommentState());
    }

}
