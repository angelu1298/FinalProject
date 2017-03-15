<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<form>
	<p class="date_area">
		<a href="">이전주</a>
		<strong>2017년 2월 2번째 주</strong>
		<a href="">다음주</a>
	</p>
	<p class="goal_area">
		<strong>목표</strong> 
	</p>

	<div class="tablelist weekdiary"> 
		<!-- 테이블 헤더 -->
		<p>
			<span>월</span>
			<span>화</span>
			<span>수</span>
			<span>목</span>
			<span>금</span>
			<span>토</span>
			<span>일</span>
		</p>
		<!-- 본문내용 -->		
		<ul>
			<li>
				<dl>
					<dt>운동</dt>
					<dd>오늘도 넘나많이먹었어여.. 돼지가되어버릴거같아요</dd>
				</dl>
				<dl class="list">
					<dt>식단목록</dt>
					<dd>순두부찌개 1인분</dd>
					<dd>제육볶음 1인분</dd>
					<dd>결과:2000kcal</dd>
				</dl> 
				<p class="result">총 ***칼로리를 드셨어요!</p>
			</li>
			<li>
				<dl>
					<dt>운동</dt>
					<dd>오늘도 넘나많이먹었어여.. 돼지가되어버릴거같아요</dd>
				</dl>
				<dl class="list">
					<dt>식단목록</dt>
					<dd>순두부찌개 1인분</dd>
					<dd>제육볶음 1인분</dd>
					<dd>결과:2000kcal</dd>
				</dl> 
				<p class="result">총 ***칼로리를 드셨어요!</p>
			</li>
			<li>
				<dl>
					<dt>운동</dt>
					<dd>오늘도 넘나많이먹었어여.. 돼지가되어버릴거같아요</dd>
				</dl>
				<dl class="list">
					<dt>식단목록</dt>
					<dd>순두부찌개 1인분</dd>
					<dd>제육볶음 1인분</dd>
					<dd>결과:2000kcal</dd>
				</dl> 
				<p class="result">총 ***칼로리를 드셨어요!</p>
			</li>
			<li>
				<dl>
					<dt>운동</dt>
					<dd>
						<textarea>오늘도 넘나많이먹었어여.. 돼지가되어버릴거같아요</textarea>
						<span class="tip">오늘의 운동량을 기입해주세요</span>
					</dd>
				</dl>
				<dl class="list">
					<dt>식단목록<input type="button" value="추가" /></dt>
					<dd>순두부찌개 1인분</dd>
					<dd>제육볶음 1인분</dd>
					<dd>결과:2000kcal</dd>
				</dl> 
				<p class="result">오늘은 현재시간까지 총 ***칼로리를 드셨어요!</p>
			</li>
			
			<!-- 오늘기준 미래 시점은 데이터를 표시하지 않음. -->
			<li class="no">
				<p class="nodata">해당 날짜의 데이터는 아직 입력할 수 없습니다.</p>
			</li>
			<li class="no">
				<p class="nodata">해당 날짜의 데이터는 아직 입력할 수 없습니다.</p>
			</li>
			<li class="no">
				<p class="nodata">해당 날짜의 데이터는 아직 입력할 수 없습니다.</p>
			</li>
		</ul>
		
	</div>
	<div class="btnarea">

		<p class="fotgoal">	
			<!-- 버튼클릭시, disabled해제 -->
			<strong>주간목표</strong>
			<input type="text" disabled="disabled" class="weekgoal">
			<input type="button" class="btn_week_edit">
		</p>

		<p class="fr">
			<input type="submit" value="저장" />
		</p>
	</div>
	</form>
	