from db import db  # 변경된 임포트 경로

class MemberGenre(db.Model):
    __tablename__ = "member_genre_table"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    member_entity_id = db.Column(db.Integer, db.ForeignKey('member_table.id'))
    genre_entity_id = db.Column(db.Integer, db.ForeignKey('genre_table.id'))

    # 관계 설정
    member = db.relationship('Member', back_populates='genres')
    genre = db.relationship('Genre', back_populates='members')

    def __repr__(self):
        return f"<MemberGenre {self.id}, {self.member_entity_id}, {self.genre_entity_id}>"
