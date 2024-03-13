package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private String username;

        public Board toEntity(){
            return new Board(title, content, username);
        }
    }

    @Data
    public static class UpdateDTO{
        private String title;
        private String content;
        private String username;
    } // SaveDTO랑 필드는 똑같을 수 있어도 로직이 다를 수 있기 때문에 UpdateDTO를 만든다.
}
