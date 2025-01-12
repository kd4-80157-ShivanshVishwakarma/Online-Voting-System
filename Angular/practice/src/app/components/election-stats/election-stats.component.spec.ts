import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElectionStatsComponent } from './election-stats.component';

describe('ElectionStatsComponent', () => {
  let component: ElectionStatsComponent;
  let fixture: ComponentFixture<ElectionStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElectionStatsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElectionStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
