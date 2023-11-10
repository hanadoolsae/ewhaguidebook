from app import db  # SQLAlchemy 인스턴스를 app.py로부터 임포트

class MemberMyLike(db.Model):
    __tablename__ = "member_mylike_table"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    member_entity_id = db.Column(db.Integer, db.ForeignKey('member_table.id'), nullable=False)
    book_entity_id = db.Column(db.Integer, db.ForeignKey('book_table.id'), nullable=False)

    # Relationships
    member = db.relationship('Member', back_populates='my_likes')  # 'my_likes'로 수정
    book = db.relationship('Book', back_populates='memberMyLikes')  # Book 모델의 관계 이름에 맞게 수정

    def __repr__(self):
        return f"<MemberMyLike {self.id}, {self.member_entity_id}, {self.book_entity_id}>"
