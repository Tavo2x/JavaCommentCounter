public interface State {

//- States to handle
    void forwardSlash(CommentCounter c);
    void backwardSlash(CommentCounter c);
    void star(CommentCounter c);
    void newLine(CommentCounter c);
    void doubleQuote(CommentCounter c);
    void singleQuote(CommentCounter c);
    void other(CommentCounter c, char symbol);

}
