# Myself
한줄한줄 이해하며 스스로 만들어보기

1일차 : 로그인 및 회원가입 기능만 구현
> 로그인 기능 : Springsecurity를 사용하여 구현
> 회원가입 기능 : repository.save를 이용해 데이터베이스에 저장만 되도록 설정하였음

2일차 : 회원가입시 생기는 오류에 대한 설정 + 부트스트랩을 활용하여 front꾸미기
> 1. 회원가입시에 일어날 수 있는 아이디를 적지않거나 비밀번호를 적지않을 때 그대로 문제없이 회원가입이 진행되면 안되기 때문에 락을 걸었음
> 2. 부트스트랩의 Navbar를 활용하여 메인페이지 상단에 로그인 버튼을 두고 로그인 버튼을 눌렀을 때 자신의 아이디를 치고 로그인하는 버튼과 회원가입버튼을 두었음

3일차 : 게시판을 만들 것이였기 때문에 게시판에관한 DB 생성 및 다양한 기능 구현
> 1. 하나의 유저는 여러개의 게시글을 쓸수 있기 때문에 @ManyToOne 어노테이션을 활용하여 User테이블의 id를 참조하게 설정
> 2. 게시글 전체 목록에 게시글 번호, 작성자, 제목이 나오게함
> 3. 게시글 목록에서 제목클릭시 게시글에 대한 내용이 나옴
> 4. 게시글을 새롭게 작성할 때또한 제목과 내용부분에 아무것도 적지않으면 오류메시지를 
> 5. 만약 게시글을 작성한 본인이라면 게시글에 대한 수정 및 삭제권한할 수 있음 즉, 권한에 따라 수정 및 삭제가능

4일차 : 게시글 페이징처리, 검색기능, 각 게시글에 대한 댓글작성 가능
> 1. querydsl을 이용하여 페이징 및 검색기능을 처리해보려 하였으나 실패, 다양한 방법이 있겠지만 클론코딩을 하며 알게된 방법 사용-> 코딩이 좀 지저분하고 난잡해보임, 다른 방법도 추가적으로 공부해볼 필요가있음
> 2. 그냥 검색기능을 만들기보단 조건부 검색기능을 만들어보았음 제목을 누르고 검색시 제목에 글자가 포함된 리스트들만 출력, 내용누르면 내용안에 해당글이 담겨있는 리스트들 출력 -> 이 또한 정상적으로 작동하고 스스로 만들어본것이라 효율적인 면에서 좋은 방법같아 보이지는 않음 다양한 방법 구상해볼 필요가있음
> 3. 댓글작성위해 reply 데이터베이스를 만들고 @ManyToOne로 board_id와 user_id를 참조하게 하였음. 각각 해당하는 게시글에 대한 댓글만 출력할 수 있게 하였다.

5일차 : 댓글 삭제 기능 및 view부분 다듬기

--게시판 프로젝트 종료--!

내가 지금 까지 클론 코딩한것에 대한 어느정도의 이해와 활용을 바탕으로 강의를 보고 따라하거나 다른 부분의 코딩을 그대로 배껴오는 것이 아닌 나만의 블로그를 만들어보았다.
다른 강의에서 난 이런것도 넣어보고싶은데 안가르쳐주네 하면서 처음에는 그냥 어쩔수 없지 하고 지나갔지만 여러개의 클론 코딩을 해보며 계속 만들어 보고자 하는 마음이 커졌고 이번에 나만의 게시판을 만들어보면서 어느정도 해소되었다는 느낌이 들었다. 분명 최상의 방법은 아닐거라고 확신한다. 하지만 기능 자체를 구현해보았고 그것이 오류나 속도 저하등을 일으키지는 않았으니 나름 만족스러운 나만의 첫 프로젝트라고 생각한다. 분명 실무위주의 클론 코딩을 하며 그로직을 이해하고 다른곳에 쓰는 것도 중요하지만 기본적인 이론부분도 공부할 필요성을 느꼈고 강의를 들으며 이론공부를 하고 
새로운 프로젝트를 만들던지, 다른 마음에 드는 클론코딩을 진행하며 정확한 이해를 기반으로 해야겠다.
지금까지는 조금 모르겠어도 일단 진행해보자라는 식의 내눈에 익게하고 느낌을 받아보는 클론코딩이였다면 앞으로는 좀 더 정확하고 확실한 기능및 로직의 순환? 을 제대로 알고 진행해보아야겠다
