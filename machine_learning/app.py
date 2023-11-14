from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS
from flask_restx import Api

# 전역 변수로 db 인스턴스를 설정합니다.
db = SQLAlchemy()

def create_app():
    app = Flask(__name__)

    # DB 설정
    app.config["SQLALCHEMY_DATABASE_URI"] = "mysql+pymysql://testuser01:1234@13.124.172.94/ewha02guidebook"
    app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
    app.config['SQLALCHEMY_POOL_RECYCLE'] = 600
    app.config['SQLALCHEMY_POOL_TIMEOUT'] = 10
    app.config['SQLALCHEMY_POOL_SIZE'] = 30
    app.config['SQLALCHEMY_MAX_OVERFLOW'] = 10

    # SQLAlchemy 인스턴스에 앱을 등록합니다.
    db.init_app(app)

    # CORS 설정
    CORS(app, resources={r"/*": {"origins": "*"}})

    # 앱 컨텍스트 내에서 데이터베이스를 초기화합니다.
    with app.app_context():
        db.create_all()

        # Blueprint 등록
        from apis.recommendation import recommendation_blueprint
        app.register_blueprint(recommendation_blueprint, url_prefix="/ml/api/recommend")

    return app

# Flask 애플리케이션 인스턴스 생성
app = create_app()

if __name__ == "__main__":
    # 애플리케이션 실행
    app.run(host='0.0.0.0', port=5000)
