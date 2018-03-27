# junkieutils
utils for junkie :)

# 따라하기
1. java install
   * java -version
1. mvn install
   * mvn -version
1. git install
   * 윈도우 버전 인스톨 : https://git-scm.com/download/win
   * git --version

# 소스 다운로드 받아서 실행하기
1. git 에서 소스 다운받기
    <pre><code>
    git clone https://github.com/star16m/junkieutils.git
    Cloning into 'junkieutils'...
    remote: Counting objects: 31, done.
    remote: Compressing objects: 100% (18/18), done.
    remote: Total 31 (delta 3), reused 28 (delta 3), pack-reused 0
    Unpacking objects: 100% (31/31), done.
    </code></pre>
1. 소스 컴파일
    <pre><code>
    cd junkieutils
    mvn clean package
    </code></pre>
1. 실행
    <pre><code>
    java -jar target\search-0.0.1-SNAPSHOT.jar
    </code></pre>
1. browser 확인
    * 전체 등록된 검색어 : http://localhost:8080/api/searchword/
    * 특정 검색어 등록 및 확인 http://localhost:8080/api/searchword/<검색어>


# 사용한 내용
1. spring boot 로 최소 구성
2. jsoup 으로 naver 검색(확장 가능)
3. 확장가능 하지만 일단 연관검색어만 추가.
4. db 쪽은 h2(default)이며, 휘발성(datasource 추가하면 됨)
5. rest api 쪽은 제대로 구성 안했고, get 2개만 해두었음