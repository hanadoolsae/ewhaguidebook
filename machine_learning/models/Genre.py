from app import db  # SQLAlchemy 인스턴스를 app.py로부터 임포트

class Genre(db.Model):
    __tablename__ = "genre_table"

    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    genreName = db.Column('genre_name', db.String(255), nullable=False)

    # 관계 설정 - MemberGenre 클래스와 연결
    members = db.relationship('MemberGenre', back_populates='genre')

    def __repr__(self):
        return f"<Genre {self.id}, '{self.genreName}'>"