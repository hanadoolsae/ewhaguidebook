# 🖥️ 도서 데이터 크롤링 하는 법
## 진행과정
**1. 웹사이트 선정 및 탐색**
- 저희는 교보문고가 청소년 도서 목록 정리가 더 깔끔해서 교보문고를 활용하였습니다.</br>
**2. 웹 크롤링 도구 선택**
- BeautifulSoup를 보통 많이 사용하는데, 저는 브라우저 자동화 도구인 Selenium을 사용하여 진행하였습니다.</br>
**3. 데이터 수집**
- 선택한 웹사이트에서 도서에 관한 정보를 크롤링합니다.</br>
주로 사용되는 정보는 도서 제목, 작가, 출판사, 가격, 출판일, 리뷰 등이 있습니다.</br>
저희는 도서 개요, 표지이미지, 쪽수를 크롤링하였습니다.</br>
크롤링을 할 때는 정보가 html의 어느 부분에 위치해있는지 정확히 확인하는 것이 중요합니다.</br>
**4. 데이터 저장**
- 보통은 csv 파일이나 excel파일로 저장을 많이하고, 저도 excel로 저장하였습니다.</br>


# 🖥️ bard를 활용하는 법
## 진행과정
**1. 도서 데이터 준비**
**2. Bard의 key 값 입력**
- key값은 Bard 사이트에서 f12를 눌러서 개발자 도구를 실행시키고, 쿠키에 들어가서 Secure-1PSID Value값을 가져오면 됩니다.</br>
(Value값은 마지막에 온점(.)으로 끝이 납니다.)</br>

**3. 프롬프트 작성**
- 프롬프트는 최대한 자세하게 작성할 수록 좋습니다.</br>
</br>
**📍 Secure-1PSID Value 에러가 나는 경우**</br>
간혹가다 코드를 돌리는데 Secure-1PSID Value 에러가 나는 경우가 있습니다.</br>
이때는, 쿠키를 지운 후 다시 Bard 사이트에서 개발자도구를 확인하면 새로운 Secure-1PSID Value를 확인할 수 있습니다.</br>
새로운 Secure-1PSID Value를 사용하면 에러가 사라집니다.</br>
</br>
**📍 Bard limit 에러**</br>
도서 난이도의 정확도가 gpt보다 Bard가 높아서 Bard를 활용하였으나, Bard가 시험버전이라 그런지 api를 따와서 돌릴 땐 질문 개수에 제한이 있었습니다.</br>
그래서 저희 도서 데이터가 약 1만8천권이다 보니 중간에 limit가 걸리는 경우가 생겨 며칠에 거쳐서 데이터를 돌렸습니다.</br>
(만약, Bard 활용 목적이 다르다면 gpt나 clova X를 활용하는 것이 더 좋을 것 같습니다.)</br>



# 🖥️ 임베딩 모델 비교
## 한국어 모델
**KoBERT(KoreanBidirectional Encoder Representations from Transformers)**</br>
SKT에서 공개한 위키피디아, 뉴스 등에서 수집한 5천만개의 문장으로 학습된 모델입니다. </br>
한국어의 불규칙한 언어 변화의 특성을 반영하기 위해 데이터 기반 토큰화(SentencePiece tokenizer) 기법을 적용하였으며 vocab 크기는 8002, 모델의 파라미터 크기는 92M입니다.</br>

**KLUE-BERT**</br>
KLUE-BERT는 벤치마크 데이터인 KLUE에서 베이스라인으로 사용되었던 모델로, 모두의 말뭉치, CC-100-Kor, 나무위키, 뉴스, 청원 등 문서에서 추출한 63GB의 데이터로 학습되었습니다. </br>
Morpheme-based Subword Tokenizer를 사용하였으며, vocab size는32,000이고 모델의 크기는 111M입니다.</br>

**📃 References**</br>
https://sktelecom.github.io/project/kobert/</br>
https://github.com/SKTBrain/KoBERT</br>
https://huggingface.co/klue/bert-base?text=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%EC%9D%98+%EC%88%98%EB%8F%84%EB%8A%94+%5BMASK%5D+%EC%9E%85%EB%8B%88%EB%8B%A4.</br>
https://github.com/KLUE-benchmark/KLUE</br>
https://cpm0722.github.io/paper-review/an-empirical-study-of-tokenization-strategies-for-various-korean-nlp-tasks</br>


