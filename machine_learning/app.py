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

    db.init_app(app)
    CORS(app, resources={r"/*": {"origins": "*"}})

    # Blueprint 등록을 위해 아래의 코드로 변경합니다.
    with app.app_context():
        from apis.recommendation import recommendation_blueprint  # 여기로 이동
        app.register_blueprint(recommendation_blueprint, url_prefix="/ml/api/recommend")

    return app


if __name__ == "__main__":
    app = create_app()
    app.run(host='127.0.0.1', port=5000)

