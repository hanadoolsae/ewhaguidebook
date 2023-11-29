<h3 data-ke-size="size23"><b>GPT와 Bard를 사용하게 된 이유</b></h3>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">저희가 도서 난이도를 판별하는 방법으로 원래는 대교의 KreaD를 사용하려고 했어요.</p>
<p data-ke-size="size16">KreaD는 대교에서 만든 서비스로 '텍스트의 난이도'를 알려줍니다.</p>
<p data-ke-size="size16"><a title="대교 KreaD" href="https://www.kread.ai/kread/experience" target="_blank" rel="noopener">https://www.kread.ai/kread/experience</a></p>
<figure id="og_1684480186822" contenteditable="false" data-ke-type="opengraph" data-ke-align="alignCenter" data-og-type="website" data-og-title="http://sso3.daekyo.com/WebSSO/AuthWeb/ssoService.do?returnURL=https%3A%2F%2Fwww.kread.ai&amp;ssosite=www.kread.ai" data-og-description="" data-og-host="sso3.daekyo.com" data-og-source-url="https://www.kread.ai/kread/experience" data-og-url="http://sso3.daekyo.com/WebSSO/AuthWeb/ssoService.do?returnURL=https%3A%2F%2Fwww.kread.ai&amp;ssosite=www.kread.ai" data-og-image=""><a href="https://www.kread.ai/kread/experience" target="_blank" rel="noopener" data-source-url="https://www.kread.ai/kread/experience">
<div class="og-image" style="background-image: url();">&nbsp;</div>
<div class="og-text">
<p class="og-title" data-ke-size="size16">http://sso3.daekyo.com/WebSSO/AuthWeb/ssoService.do?returnURL=https%3A%2F%2Fwww.kread.ai&amp;ssosite=www.kread.ai</p>
<p class="og-desc" data-ke-size="size16">&nbsp;</p>
<p class="og-host" data-ke-size="size16">sso3.daekyo.com</p>
</div>
</a></figure>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">이 말은 즉, 도서의 텍스트 파일이 있어야 한단 뜻이고,</p>
<p data-ke-size="size16">저희는 시간과 노동력이 가장 남아돌았기에 약 1만 권의 책의 샘플 데이터를 직접 타이핑해서 돌릴 예정이었습니다.</p>
<p data-ke-size="size16">그러나 이게 되게 무식한 방법이고, 지속가능성이 낮고, <s>대교 측과의 협업이 불발되어버려서,,</s></p>
<p data-ke-size="size16">&nbsp;</p>
<p>[##_Image|kage@DM0sZ/btsg86wkvrl/uGs5hy82nsgrlNzE32b0HK/img.jpg|CDM|1.3|{"originWidth":4095,"originHeight":2151,"style":"alignCenter","width":390,"height":205}_##]</p>
<p data-ke-size="size16">GPT나 Bard와 같은 AI를 사용하기로 생각했어요.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">AI들이 모든 도서의 텍스트를 가지고 있는 것은 아니기에</p>
<p data-ke-size="size16">KreaD만큼의 세밀한 난이도 산출은 어렵지만</p>
<p data-ke-size="size16">작가에 대한 정보를 바탕으로 문체나 전반적인 어휘의 난이도를 고려할&nbsp; 수 있고,</p>
<p data-ke-size="size16">장르 및 도서의 주제를 고려하여 산출해 주기에 오히려 좋았습니다,,</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">그리고 사실 도서 난이도 산출에 대한 <b>정확도</b>를 논하는 것은 약간 모순적인 것이</p>
<p data-ke-size="size16">책을 읽는 유저마다 성향과 배경지식 등이 다 다르기에 난이도 설정에 차이가 생길 수밖에 없어</p>
<p data-ke-size="size16">모든 유저가 납득하고 만족할만한 난이도 분류는 있을 수 없다고 생각합니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">그래서 저희는 저희가 제공하는 기준이 '옳다'라고 말할 순 없지만,</p>
<p data-ke-size="size16">최대한 통일된 기준을 가지고 책을 분류하는 작업을 진행하려고 했고,</p>
<p data-ke-size="size16">그래서 GPT와 Bard를 활용하게 되었어요.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<hr contenteditable="false" data-ke-type="horizontalRule" data-ke-style="style6" />
<h3 data-ke-size="size23"><b>활용 방향</b></h3>
<p data-ke-size="size16">저희가 생각한 방식은</p>
<p data-ke-size="size16">1. 출판사 별로 제공하는 도서 데이터를 다운로드한다.</p>
<p data-ke-size="size16">2. 데이터에서 제목/작가/장르/페이지수 의 정보를 제외하고는 삭제한다.</p>
<p data-ke-size="size16">3. GPT 또는 Bard의 API key를 받아와서 import 한다.</p>
<p data-ke-size="size16">4. 엑셀 파일을 행마다 돌리면서 책의 정보를 읽고 prompt로 질문 넘긴다.</p>
<p data-ke-size="size16">5. 출력된 대답을 엑셀의 새로운 열에 삽입한다.</p>
<p data-ke-size="size16">6. 출력 값을 정제하여 책의 난이도만 추출한다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">이러한 방식으로 책의 난이도를 판별하고자 하였습니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<hr contenteditable="false" data-ke-type="horizontalRule" data-ke-style="style6" />
<h3 data-ke-size="size23"><b>진행 과정</b></h3>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size18"><b>1. 출판사 별로 제공하는 도서 데이터를 다운로드한다.</b></p>
<p data-ke-size="size16">이건 각 출판사 별로 홈페이지에 들어가면 데이터를 얻을 수 있습니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">민음사의 경우에는 아래 링크로 들어가시면 도서 목록을 다운로드할 수 있어요.</p>
<p data-ke-size="size16"><a title="민음사 도서 데이터" href="http://minumsa.com/booklist-download/" target="_blank" rel="noopener">http://minumsa.com/booklist-download/</a></p>
<figure id="og_1684296730753" contenteditable="false" data-ke-type="opengraph" data-ke-align="alignCenter" data-og-type="website" data-og-title="도서목록 다운로드 | 민음사 출판그룹" data-og-description="" data-og-host="minumsa.com" data-og-source-url="http://minumsa.com/booklist-download/" data-og-url="http://minumsa.com/booklist-download/" data-og-image="https://scrap.kakaocdn.net/dn/ZcfKi/hySE1kr9RQ/MJceZEylmb7kyOKvzQnpR0/img.jpg?width=500&amp;height=500&amp;face=0_0_500_500"><a href="http://minumsa.com/booklist-download/" target="_blank" rel="noopener" data-source-url="http://minumsa.com/booklist-download/">
<div class="og-image" style="background-image: url('https://scrap.kakaocdn.net/dn/ZcfKi/hySE1kr9RQ/MJceZEylmb7kyOKvzQnpR0/img.jpg?width=500&amp;height=500&amp;face=0_0_500_500');">&nbsp;</div>
<div class="og-text">
<p class="og-title" data-ke-size="size16">도서목록 다운로드 | 민음사 출판그룹</p>
<p class="og-desc" data-ke-size="size16">&nbsp;</p>
<p class="og-host" data-ke-size="size16">minumsa.com</p>
</div>
</a></figure>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size18"><b>2. 데이터에서 제목/작가/장르/페이지수 의 정보를 제외하고는 삭제한다.</b></p>
<p>[##_Image|kage@PhWYu/btsgkiwR2Xk/bLypkWlTNACvrOk0DKYUOk/img.png|CDM|1.3|{"originWidth":1046,"originHeight":370,"style":"alignCenter","caption":"1. 민음사 도서 데이터","filename":"edited_스크린샷 2023-05-17 오후 1.13.52.png"}_##]</p>
<p data-ke-size="size16">여기서 저희는 제목, 작가, 분류(장르), 페이지수의 정보만 남기고 삭제할 거예요.</p>
<p data-ke-size="size16">그리고 여러 출판사에서 이런 식으로 데이터를 받아와서 합쳐 줍니다.</p>
<p>[##_Image|kage@bsEL3Q/btsgkiRjkUq/KoqZtxio3srZykBl1E3LC0/img.png|CDM|1.3|{"originWidth":1730,"originHeight":710,"style":"alignCenter","caption":"2. 도서 목록","filename":"스크린샷 2023-05-17 오후 1.30.51.png"}_##]</p>
<p data-ke-size="size16">그러면 이런 식으로 도서 목록을 만들 수 있습니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size18"><b>3. GPT 또는 Bard의 API key를 받아와서 import 한다.</b></p>
<p data-ke-size="size16">GPT와 Bard의 API를 가져오는 방식이 달라서 각각 설명하겠습니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16"><b>1) GPT</b></p>
<p data-ke-size="size16"><a title="OpenAI" href="https://platform.openai.com/overview" target="_blank" rel="noopener">https://platform.openai.com/overview</a></p>
<figure id="og_1684297173614" contenteditable="false" data-ke-type="opengraph" data-ke-align="alignCenter" data-og-type="website" data-og-title="OpenAI API" data-og-description="An API for accessing new AI models developed by OpenAI" data-og-host="platform.openai.com" data-og-source-url="https://platform.openai.com/overview" data-og-url="https://platform.openai.com" data-og-image=""><a href="https://platform.openai.com/overview" target="_blank" rel="noopener" data-source-url="https://platform.openai.com/overview">
<div class="og-image" style="background-image: url();">&nbsp;</div>
<div class="og-text">
<p class="og-title" data-ke-size="size16">OpenAI API</p>
<p class="og-desc" data-ke-size="size16">An API for accessing new AI models developed by OpenAI</p>
<p class="og-host" data-ke-size="size16">platform.openai.com</p>
</div>
</a></figure>
<p data-ke-size="size16">위의 링크로 들어가서 회원가입을 해주고 API key를 발급받으면 됩니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p>[##_Image|kage@4Gh8I/btsgcOc5nW5/1CembH6peyriJAVy6gyFB1/img.png|CDM|1.3|{"originWidth":1664,"originHeight":710,"style":"alignCenter","caption":"3. OpenAI key 발급","filename":"edited_스크린샷 2023-05-17 오후 1.20.29.png"}_##]</p>
<p data-ke-size="size16">저는 이미 key를 발급받은 상태인데, 이 화면에서 'Create new seceret key'를 클릭하고 발급받으면 됩니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p>[##_Image|kage@cAE2yU/btsg0uZcbqv/KCcv0fLcHEI99wF0fosxsk/img.png|CDM|1.3|{"originWidth":1610,"originHeight":1038,"style":"alignCenter","caption":"4. OpenAI usage 확인","filename":"edited_스크린샷 2023-05-23 오후 12.36.18.png"}_##]</p>
<p data-ke-size="size16">그렇게 발급받은 key는 본인의 유효기간 동안 사용하거나, $5 범위 내로 사용할 수 있습니다.</p>
<p data-ke-size="size16">저는 너무 많이 사용해서 유효기간이 남았음에도 $5를 넘겨서 지금 사용 못하고 있어요.</p>
<p data-ke-size="size16">그래서 Bard로 넘어가게 되었답니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">암튼 이렇게 key값을 불러왔다면,</p>
<pre id="code_1684813112853" class="haskell" style="background-color: #f8f8f8; color: #383a42; text-align: start;" data-ke-language="python" data-ke-type="codeblock"><code>!pip install openai

import openai
import openpyxl
import nltk
import pandas as pd
import re
import torch


openai.api_key = "Your API Key"
openai.Model.list()

# GPT-3.5 모델 세팅
model_engine = "text-davinci-003"</code></pre>
<p data-ke-size="size16">이렇게 기본 세팅까지 해주면 된답니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16"><b>2) Bard</b></p>
<p data-ke-size="size16">Bard는 사실 현재 실험 버전입니다.</p>
<p data-ke-size="size16">그래서인지 Bard는 GPT와는 방식이 조금 다릅니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<pre id="code_1684812413177" class="python" data-ke-language="python" data-ke-type="codeblock"><code>!pip install bardapi&gt;=0.1.8
!pip install transformers</code></pre>
<p data-ke-size="size16">먼저 bardapi를 설치해 줍니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<pre id="code_1684812468252" class="python" data-ke-language="python" data-ke-type="codeblock"><code>import os
import bardapi
import openpyxl
import pandas as pd
from transformers import AutoModelForSequenceClassification, AutoTokenizer
from bardapi import Bard

token='YOUR_KEY'</code></pre>
<p data-ke-size="size16">그리고 token에 각자의 key값을 넣으면 됩니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p>[##_Image|kage@uNtoI/btsg1uxxe2o/L87vDfbMDct05oSrqAQwT1/img.png|CDM|1.3|{"originWidth":3000,"originHeight":1494,"style":"alignCenter","caption":"5. Bard 개발자 도구 실행 화면","filename":"edited_스크린샷 2023-05-23 오후 12.30.50.png"}_##]</p>
<p data-ke-size="size16">key값은 Bard 사이트에서 f12를 눌러서 개발자 도구를 실행시키고,</p>
<p data-ke-size="size16">쿠키에 들어가서 Secure-1PSID Value값을 가져오면 됩니다.</p>
<p data-ke-size="size16">Value는 마지막에 온점(.)으로 끝이 납니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size18"><b>4. 엑셀 파일을 행마다 돌리면서 책의 정보를 읽고 prompt로 질문 넘긴다.</b></p>
<p data-ke-size="size16">저희는 원래 GPT를 사용했는데, GPT의 OpenAI key값의 무료버전이 끝나면서</p>
<p data-ke-size="size16">결제를 할지, 새로운 방식을 고안해야 할지 고민했습니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">결제를 하기엔 아까웠던 이유가</p>
<p data-ke-size="size16">저희는 책의 난이도를 분류할 때 GPT를 활용하고 나면, 그 이후로는 사용하지 않을 것 같아</p>
<p data-ke-size="size16">결제를 해도 그만큼 뽕 뽑지 못할 것 같았기 때문이죠,,</p>
<p data-ke-size="size16">그래서 주변인들에게 구걸해 주변인들의 무료 API key를 적선받아 데이터만 빠르게 돌려야 할지 고민했습니다.</p>
<p data-ke-size="size16">하지만 이것도 안정적이진 못하다고 생각이 들어 결국엔 Bard를 활용하게 됐으나</p>
<p data-ke-size="size16">GPT를 활용하려고 짠 코드에서 크게 달라진 건 없어서 오히려 좋았습니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">아래 코드는 GPT를 활용할 당시 작성한 코드입니다.</p>
<pre id="code_1684375205356" class="python" data-ke-language="python" data-ke-type="codeblock"><code>prompt = (f"This Excel file contains the following information: Title of the book, Publisher, Author, Category.\n"
          f"Read each row from this Excel file and use the information to determine what kind of book it is and assign a difficulty rating from 1 to 5. "
          f"The rating should be determined by considering various factors such as complexity of the content, language level, and subject matter. \n"
          f"For each Korean book, output the title, publisher, author, number of pages, category, and the difficulty rating.\n")


# 엑셀 파일 로딩
workbook = openpyxl.load_workbook("Book_Data")
sheet = workbook.active

# 필요한 열 추출
data = pd.DataFrame(sheet.values)
book_data = data.iloc[1:, [0, 1, 2, 3]]
book_data.columns = ["title", "author", "category", "publisher"]

def get_book_difficulty(title, author, category, publisher):
    if title is None or author is None or category is None or publisher is None:
        return "Not available" 
    # 각 책마다 정보 돌리기
    for i, row in book_data.iterrows():
      book_info = f"Book: {row['title']} by {row['author']}, Category: {row['category']}, Publisher: {row['publisher']}"
      prompt_with_book_info = prompt + book_info
        
      response = openai.Completion.create(
          engine=model_engine,
          prompt=prompt_with_book_info,
          max_tokens=1024,
          n=1,
          stop=None,
          temperature=0.7,
        )
        
      # 생성된 텍스트에서 난이도 등급 추출
      difficulty = response.choices[0].text.strip()</code></pre>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">prompt는 쉽게 말하면 Chat-GPT에 넣는 질문입니다.</p>
<p data-ke-size="size16">Chat-GPT를 써보신 분들은 아시겠지만, 질문이 구체적이고, 또 자세할수록 GPT의 대답이 달라지죠.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">제가 듣는 수업에서 한 교수님께서 이런 말씀을 하셨습니다.</p>
<p data-ke-size="size16"><i>"GPT는 가스라이팅을 해야 한다 ,, "</i></p>
<p data-ke-size="size16">아주 맞는 말입니다.</p>
<p data-ke-size="size16">이 자식은 아주 맹랑하고 뻔뻔해서 틀린 답도 당당하게 뱉어냄으로써 저희를 혼란에 빠트리거든요.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">그래서 prompt를 자세하게 써야 합니다.</p>
<p data-ke-size="size16">그리고 기왕 작성하는 거 영어로 작성해야 좋습니다.</p>
<p data-ke-size="size16">그래서 제가 작성한 prompt는</p>
<p data-ke-size="size16">'엑셀 파일에는 제목/작가/출판사/장르의 정보가 있다.</p>
<p data-ke-size="size16">작가의 문체, 책의 두께, 어휘의 복잡성 등을 고려하여 책의 난이도를 1부터 5까지로 표현해라.'입니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">그리고 엑셀 파일을 불러온 이후에</p>
<p data-ke-size="size16">prompt랑 행마다 읽은 책의 정보를 합쳐서 최종 prompt로 만듭니다.</p>
<p data-ke-size="size16">(코드에서는 prompt_with_book_info로 작성됨.)</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">그리고 response는 GPT의 대답인데,</p>
<p data-ke-size="size16">여기서 engine/max_tokens/n/temperature 등을 조절해 주면 대답이 달라집니다.</p>
<p data-ke-size="size16">engine은 OpenAI의 어떤 모델을 사용하는지입니다. 이 코드에서는 text-davinchi-003을 사용하는 중이고요.</p>
<p data-ke-size="size16">max_tokens는 이 언어모델이 응답으로 생성할 수 있는 최대 토큰입니다.</p>
<p data-ke-size="size16">n은 응답의 개수로 하나의 응답만 필요하기에 1로 설정했습니다.</p>
<p data-ke-size="size16">temperature은 언어 모델의 창의성 정도라고 생각하면 됩니다.</p>
<p data-ke-size="size16">0과 2 사이의 숫자로 작성 가능한데 0.0일 땐 가장 가능성이 높은 대답을, 2일 땐 정말 다양한 응답을 생성하게 됩니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">Bard도 비슷하게 사용하면 되는데요.</p>
<pre id="code_1684816696604" class="python" data-ke-language="python" data-ke-type="codeblock"><code># 엑셀 파일 로딩
workbook = openpyxl.load_workbook("Book_data")
sheet = workbook.active# 필요한 열 추출
data = pd.DataFrame(sheet.values)

book_data = data.iloc[1:, [0, 1, 2, 3]]
book_data.columns = ["title", "author", "category", "publisher"]

def get_book_difficulty(title, author, category, publisher):
    if title is None or author is None or category is None or publisher is None:
        return "Not available"

    book_info = f"Book: {title} by {author}, Category: {category}, Publisher: {publisher}"
    prompt_with_book_info = (
        "This Excel file contains the following information: Title of the book, Publisher, Author, Category.\n"
        f"Read each row from this Excel file and use the information to determine what kind of book it is and assign a difficulty rating from 1 to 5. "
        f"The rating should be determined by considering various factors such as complexity of the content, language level, and subject matter. \n"
        f"For each book, output the title, author and the difficulty rating, and summarize the reason why you determine the difficulty like that.\n"
        + book_info
    )

    response = bardapi.core.Bard(token).get_answer(prompt_with_book_info)

    difficulty = response['content'].strip()

    return difficulty</code></pre>
<p data-ke-size="size16">Bard도 GPT에서 활용한 코드랑 비슷한데</p>
<p data-ke-size="size16">차이점은 Bard가 아직 실험 버전이라 GPT처럼 max_tokens나 temperature을 조절할 수 없습니다.</p>
<p data-ke-size="size16">그래서 response에 GPT처럼 길게 파라미터를 넣지 않았어요.</p>
<p data-ke-size="size16">이는 Bard가 발전해 나가면서 차차 개선될 부분이겠죠?</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size18"><b>5. 출력된 대답을 엑셀의 새로운 열에 삽입한다.</b></p>
<pre id="code_1684817452431" class="python" data-ke-language="python" data-ke-type="codeblock"><code># 새로운 엑셀 파일 생성
new_workbook = openpyxl.Workbook()
new_sheet = new_workbook.active
new_sheet.append(["Title", "Author", "Category", "Publisher", "Difficulty"])

# 모든 책에 대해 난이도 분류
for i, row in book_data.iterrows():
    title, author, category, publisher= row["title"], row["author"], row["category"], row["publisher"]
    difficulty = get_book_difficulty(title, author, category, publisher)
    new_sheet.append([title, author, publisher , category, difficulty])


# 새로운 엑셀 파일 저장
new_workbook.save("Book_data_result")</code></pre>
<p style="color: #333333; text-align: start;" data-ke-size="size16">기존 책 데이터에 새로운 열 Difficulty를 추가하여 출력받은 값을 넣습니다.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">그리고 난이도값이 추가된 상태의 데이터를 새롭게 저장합니다.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">이 부분 코드는 GPT랑 Bard 모두 동일합니다!</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">&nbsp;</p>
<p>[##_Image|kage@LkM4z/btsg0n0Czn5/tJwLnqX0iG9bo8jULaeV9k/img.png|CDM|1.3|{"originWidth":3010,"originHeight":1094,"style":"alignCenter","caption":"6. Bard를 통해 출력받은 값","filename":"스크린샷 2023-05-23 오후 1.56.12.png"}_##]</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">그러면 이런 식으로 결과가 나오게 됩니다.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">사실 조금 의아한 것이 왜 GPT보다 Bard의 결과가 더 좋은지 모르겠어요.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">GPT가 모르는 책도 Bard는 아주 잘 알고 있더라고요.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">암튼, 이 똑똑한 Bard는 제가 부탁한 형식에 맞춰서</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">나름의 기준에 따라 difficulty rating을 책정하고, 그 이유를 함께 출력해 줍니다.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">&nbsp;</p>
<p>[##_Image|kage@efsiWY/btsg87IwD7K/tYYJxkcTgmvwHifxUR27Ok/img.png|CDM|1.3|{"originWidth":1348,"originHeight":260,"style":"alignCenter","caption":"7. 난이도 1부터 5까지","filename":"스크린샷 2023-05-23 오후 2.26.21.png"}_##]</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">난이도 1이 나온 책부터 5가 나온 책까지 한 번 확인해 봤는데</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">큰 예외 없이 예상가능하고 이해가 되는 수준에서 책정해 주었습니다.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">&nbsp;</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">&nbsp;</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">&nbsp;</p>
<p style="color: #333333; text-align: start;" data-ke-size="size18"><b>6. 출력 값을 정제하여 책의 난이도만 추출한다.</b></p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">위에 있는 사진을 보면 알 수 있겠지만,</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">Bard 이 자식은 투머치토커라 말이 많아요.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">그래서 여기서 1부터 5까지로 표현한 도서의 난이도만 추출해낼 겁니다.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">&nbsp;</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">'6. Bard를 통해 출력받은 값' 사진을 보시면 아시겠지만,</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">Bard에서 출력받은 결과 값은</p>
<blockquote data-ke-style="style3"><i>Sure,&nbsp;I&nbsp;can&nbsp;do&nbsp;that.&nbsp;Here&nbsp;is&nbsp;the&nbsp;output&nbsp;for&nbsp;the&nbsp;book&nbsp;"인어공주&nbsp;by&nbsp;안데르센": </i><br /><br /><i>Title:&nbsp;인어공주&nbsp;(The&nbsp;Little&nbsp;Mermaid) </i><br /><i>Publisher:&nbsp;어린이&nbsp;(Children's) </i><br /><i>Author:&nbsp;안데르센&nbsp;(Hans&nbsp;Christian&nbsp;Andersen) </i><br /><i>Pages:&nbsp;120 </i><br /><i>Category:&nbsp;바로이북&nbsp;(Easy-to-read&nbsp;books) </i><br /><i>Difficulty&nbsp;rating:&nbsp;2 </i><br /><br /><i>The&nbsp;difficulty&nbsp;rating&nbsp;of&nbsp;2&nbsp;is&nbsp;based&nbsp;on&nbsp;the&nbsp;following&nbsp;factors: </i><br /><br /><i>*&nbsp;The&nbsp;book&nbsp;is&nbsp;relatively&nbsp;short,&nbsp;with&nbsp;120&nbsp;pages. </i><br /><i>*&nbsp;The&nbsp;content&nbsp;is&nbsp;not&nbsp;very&nbsp;complex,&nbsp;and&nbsp;the&nbsp;language&nbsp;is&nbsp;simple&nbsp;enough&nbsp;for&nbsp;most&nbsp;readers&nbsp;to&nbsp;understand. </i><br /><i>*&nbsp;The&nbsp;subject&nbsp;matter&nbsp;is&nbsp;familiar&nbsp;to&nbsp;most&nbsp;people,&nbsp;and&nbsp;there&nbsp;are&nbsp;no&nbsp;technical&nbsp;terms&nbsp;or&nbsp;concepts&nbsp;that&nbsp;would&nbsp;require&nbsp;special&nbsp;knowledge. </i><br /><br /><i>Overall,&nbsp;this&nbsp;book&nbsp;is&nbsp;a&nbsp;good&nbsp;choice&nbsp;for&nbsp;readers&nbsp;who&nbsp;are&nbsp;looking&nbsp;for&nbsp;an&nbsp;easy-to-read&nbsp;story.&nbsp;It&nbsp;is&nbsp;a&nbsp;classic&nbsp;tale&nbsp;that&nbsp;has&nbsp;been&nbsp;enjoyed&nbsp;by&nbsp;people&nbsp;of&nbsp;all&nbsp;ages&nbsp;for&nbsp;generations.</i><i></i></blockquote>
<p style="color: #333333; text-align: start;" data-ke-size="size16">이런 식으로 공통된 형식을 가지고 있었어요.</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">&nbsp;</p>
<p style="color: #333333; text-align: start;" data-ke-size="size16">그래서 저는 여기서 Difficulty rating: 뒤에 적힌 숫자만 남기는 과정을 진행하였습니다.</p>
<pre id="code_1684822243863" class="python" data-ke-language="python" data-ke-type="codeblock"><code># 엑셀 파일 경로 설정
excel_file_path = "Book_data_result"

# 엑셀 파일 읽기
df = pd.read_excel(excel_file_path)

# Difficulty 열의 값을 수정
df['Difficulty'] = df['Difficulty'].str.extract(r'Difficulty rating: (\d+)', expand=False)

# 수정된 데이터프레임을 새로운 엑셀 파일로 저장
output_file_path = "Book_data_real_result"
df.to_excel(output_file_path, index=False)</code></pre>
<p data-ke-size="size16">이런 식으로 진행하였고,</p>
<p>[##_Image|kage@8rAg8/btsg0VXj2ea/7ScOxACKxj4Br554wKexwK/img.png|CDM|1.3|{"originWidth":534,"originHeight":210,"style":"alignCenter","caption":"8. 출력값에서 난이도만 남기고 다 삭제하기","filename":"스크린샷 2023-05-23 오후 3.17.28.png"}_##]</p>
<p data-ke-size="size16">결과는 이렇게 Difficulty 열에 숫자만 남게 되었습니다!</p>
<p data-ke-size="size16">이렇게 되면 난이도 1부터 5까지 그룹별로 확인하기도 좋겠죠?</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">이렇게 도서의 난이도가 포함된 데이터를 만들어 보았습니다!</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<hr contenteditable="false" data-ke-type="horizontalRule" data-ke-style="style6" />
<h3 data-ke-size="size23"><b>보완해야 할 점</b></h3>
<p data-ke-size="size16"><b>1. 서비스의 타겟 유저층을 확실히 정하고 도서 범위를 한정시켜 다시 난이도를 책정하기.</b></p>
<p data-ke-size="size16">저희는 처음 기획단계에서</p>
<p data-ke-size="size16">'<u>책을 읽고자 하는 사람이라면 누구나 다 이 서비스를 이용할 수 있다.</u>'</p>
<p data-ke-size="size16">라고 생각하고 타겟 유저층을 제대로 설정하지 않았어요.</p>
<p data-ke-size="size16">그로 인해 책의 난이도를 보시면 알겠지만</p>
<p data-ke-size="size16">정말 3~5세 정도가 읽을 법한 유아책이 난이도 1을 차지하고 있어</p>
<p data-ke-size="size16">성인 기준 쉽다고 생각되는 신데렐라가 난이도 2가 되다 보니</p>
<p data-ke-size="size16">난이도 3에 많은 책이 몰리게 되어 살짝 신뢰도가 떨어지는 듯합니다.</p>
<p data-ke-size="size16">따라서 유아책을 빼고 (뺄 거면 어디까지 뺄 것인지, 신데렐라 같은 동화도 뺄 것인지) 다시 난이도를 산출해야 할 것 같아요.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16"><b>2. 출력 값에서 난이도 제외 다른 데이터를 활용할 방법 찾기.</b></p>
<p data-ke-size="size16">현재 제가 짠 코드에서는 책의 난이도 (숫자 데이터)를 제외하고는 모두 삭제하고 있는데,</p>
<p data-ke-size="size16">이렇게 되면 왜 이런 결과가 나온 것인지에 대한 <u>유의미한 데이터가 함께 삭제되어</u> 나중에 데이터를 확인할 때 어려워집니다.</p>
<p data-ke-size="size16">그래서 이 문제는 데이터에서 어떤 단어들을 살릴 것인지, 어떤 것들을 남길지 등 회의를 한 뒤 생각해 봐야겠습니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<hr contenteditable="false" data-ke-type="horizontalRule" data-ke-style="style5" />
<p data-ke-size="size16">저희는 유저가 <b>완독 </b>할 수 있는 책을 추천해주고자 합니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">'완독을 꼭 해야 하나요?'라고 질문하신다면</p>
<p data-ke-size="size16">'아니요.'라고 대답하겠지만,</p>
<p data-ke-size="size16">'완독을 하고 싶은데 못하겠어요.'라고 말씀하신다면</p>
<p data-ke-size="size16">하실 수 있게끔 도와주는 것이 저희 프로젝트의 궁극적인 목적입니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">그래서 저희는 유저들이</p>
<p data-ke-size="size16">읽고 싶은 책/읽는 중인 책/읽다 중단한 책/완독 한 책</p>
<p data-ke-size="size16">이렇게 네 가지로 책을 분류할 수 있도록 했습니다.</p>
<p data-ke-size="size16">특히, 저희는 '<b>읽다 중단한 책</b>'을 중요하게 생각했습니다.</p>
<p data-ke-size="size16">책을 읽다 만 이유는 참 다양할텐데,</p>
<p data-ke-size="size16"><u>책이 길어서(읽을 시간이 부족해서), 장르가 취향이 아니라서, 작가가 마음에 안 들어서, 책이 너무 어려워서 등</u>이 있겠죠 ?</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">저희는 이 의견을 수렴하여</p>
<p data-ke-size="size16">유저별로 <b>어느정도의 길이의 책을 선호할지, 취향저격인 장르와 작가는 누구일지, 책의 전반적인 난이도는 어떨지</b></p>
<p data-ke-size="size16">이렇게 세 가지 기준을 고려하여 추천 알고리즘을 작성할 계획입니다.</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">오늘은 그 중 책의 난이도를 분류하는 방법으로 GPT와 Bard를 사용해보았습니다.</p>
<p data-ke-size="size16">다음엔 위에서 말한 보완점을 수정해서 돌아오거나,,</p>
<p data-ke-size="size16">혹은 추천 알고리즘으로 새롭게 돌아오도록 하겠습니다,,</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p>[##_Image|kage@bj6ts8/btsg1fg9ERQ/J1Ke92Ml0mT8HmUGiWKvnK/img.gif|CDM|1.3|{"originWidth":480,"originHeight":360,"style":"alignCenter"}_##]</p>
<p data-ke-size="size16">그럼 읽어주셔서 감사합니다!</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
<p data-ke-size="size16">&nbsp;</p>
