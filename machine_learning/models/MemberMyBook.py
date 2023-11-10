from app import db  # SQLAlchemy 인스턴스를 app.py로부터 임포트

class MemberMyBook(db.Model):
    __tablename__ = "member_mybook_table"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    member_entity_id = db.Column(db.Integer, db.ForeignKey('member_table.id'), nullable=False)  # 외래 키 설정
    book_entity_id = db.Column(db.Integer, nullable=False)
    status = db.Column(db.Integer, default=0)
    status_detail = db.Column(db.String(255))
    memo = db.Column(db.String(255))

    # Member 클래스와의 양방향 관계 설정
    member = db.relationship('Member', back_populates='my_books')

    def __repr__(self):
        return f"<MemberMyBook {self.id}, {self.member_entity_id}, {self.book_entity_id}, {self.status}, '{self.status_detail}', '{self.memo}'>"
