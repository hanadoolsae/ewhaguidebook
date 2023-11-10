from app import db  # SQLAlchemy 인스턴스를 app.py로부터 임포트

class Member(db.Model):
    __tablename__ = 'member_table'

    id = db.Column(db.Integer, primary_key=True)
    member_email = db.Column(db.String(255), unique=True, nullable=False)
    member_level = db.Column(db.Integer, default=0)
    member_name = db.Column(db.String(255), nullable=False)
    member_password = db.Column(db.String(255), nullable=False)
    member_birth = db.Column(db.Date)
    member_grade = db.Column(db.String(50))
    member_major = db.Column(db.String(255))
    member_score = db.Column(db.Integer, default=1)
    member_character = db.Column(db.String(255))

    # Relationships
    genres = db.relationship('MemberGenre', back_populates='member')
    my_books = db.relationship('MemberMyBook', back_populates='member', lazy='dynamic')
    my_likes = db.relationship('MemberMyLike', back_populates='member', lazy='dynamic')

    def __repr__(self):
        return f'<Member {self.id} {self.member_name}>'

    def to_dict(self):
        return {
            'id': self.id,
            'member_email': self.member_email,
            'member_level': self.member_level,
            'member_name': self.member_name,
            'member_password': self.member_password,
            'member_birth': self.member_birth.strftime("%Y-%m-%d") if self.member_birth else None,
            'member_grade': self.member_grade,
            'member_major': self.member_major,
            'member_score': self.member_score,
            'member_character': self.member_character
        }
